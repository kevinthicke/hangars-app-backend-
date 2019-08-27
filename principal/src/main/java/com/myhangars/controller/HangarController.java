package com.myhangars.controller;

import com.myhangars.hangar.builder.HangarBuilder;
import com.myhangars.hangar.builder.HangarDtoBuilder;
import com.myhangars.hangar.dto.HangarDto;
import com.myhangars.hangar.model.Hangar;
import com.myhangars.hangar.repository.HangarRepository;
import com.myhangars.hangar.service.HangarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@Validated
@RequestMapping(value = "/api")
public class HangarController {

    @Autowired
    private HangarService hangarService;

    @GetMapping(value = "/hangars")
    public ResponseEntity<List> loadHangars() {

        List<HangarDto> hangarDtos = this.hangarService
                .getAll(null)
                .stream()
                .map(hangar -> new HangarDtoBuilder(hangar).getHangarDto())
                .collect(Collectors.toList());

        return new ResponseEntity<List>(
                hangarDtos,
                HttpStatus.OK
        );
    }

//    @CrossOrigin
    @GetMapping(value = "/hangars", params = { "page", "size" })
    public ResponseEntity<?> loadHangars(@RequestParam("page") int page,
                                            @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page,size);

        Page<HangarDto> hangarDtos = this.hangarService
                .getAll(pageable)
                .map(hangar -> new HangarDtoBuilder(hangar).getHangarDto());

        return new ResponseEntity<Page<HangarDto>>(
                hangarDtos,
                HttpStatus.OK
        );
    }
    

    @GetMapping(value = "hangars/names")
    public ResponseEntity<List> loadHangarsNames() {
        return new ResponseEntity<List>(
                this.hangarService.getAllNames(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/hangars/{id}")
    public ResponseEntity<HangarDto> loadHangarById(@PathVariable("id") @Min(1) long id) {

        final Hangar hangar = this.hangarService.getById(id);

        return new ResponseEntity<HangarDto>(
                new HangarDtoBuilder(hangar).getHangarDto(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/hangars/by-name")
    public ResponseEntity<List> loadhangarByName(@RequestParam("name") String name) {

        final List<HangarDto> hangarDtos = this.hangarService
                .getByName(name)
                .stream()
                .map(hangar -> new HangarDtoBuilder(hangar).getHangarDto())
                .collect(Collectors.toList());

        return new ResponseEntity<List>(
                hangarDtos,
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/hangars")
    public ResponseEntity<HangarDto> saveHangar(@Valid @RequestBody HangarDto hangarDto) {

        //TODO: Validar que HangarDto no sea null;

        final Hangar hangar = new HangarBuilder(hangarDto).getHangar();
        final Hangar hangarResponse = this.hangarService.insert(hangar);

        return new ResponseEntity<HangarDto>(
            new HangarDtoBuilder(hangarResponse).getHangarDto(),
            HttpStatus.CREATED
        );
    }

    @PutMapping(value = "/hangars/{id}")
    public ResponseEntity<HangarDto> updateHangar(@PathVariable("id") @Min(1) long id,
                                                  @Valid @RequestBody HangarDto hangarDto) {
        //TODO: Validar que HangarDto no sea null;


        final Hangar hangar = this.hangarService.update(
                id,
                new HangarBuilder(hangarDto).getHangar()
        );

        return new ResponseEntity<HangarDto>(
                new HangarDtoBuilder(hangar).getHangarDto(),
                HttpStatus.CREATED
        );
    }

    @PutMapping(value = "/hangars/safe-delete/{id}")
    public ResponseEntity<HangarDto> safeDelete(@PathVariable("id") @Min(1) long id) {
        return new ResponseEntity<HangarDto>(
                new HangarDtoBuilder(this.hangarService.safeDeleteById(id)).getHangarDto(),
                HttpStatus.OK
        );
    }
}
