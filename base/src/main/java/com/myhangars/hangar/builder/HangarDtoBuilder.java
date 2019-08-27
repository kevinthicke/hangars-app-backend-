package com.myhangars.hangar.builder;

import com.myhangars.hangar.dto.HangarDto;
import com.myhangars.hangar.model.Hangar;

public class HangarDtoBuilder {

    private HangarDto hangarDto;

    public HangarDtoBuilder(Hangar hangar) {
        this.hangarDto = new HangarDto();

        this.hangarDto.setId(hangar.getId());
        this.hangarDto.setName(hangar.getName());
        this.hangarDto.setLocation(hangar.getLocation());
        this.hangarDto.setOwner(hangar.getOwner());
        this.hangarDto.setEmail(hangar.getEmail());
        this.hangarDto.setPhone(hangar.getPhone());
    }

    public HangarDto getHangarDto() {
        return hangarDto;
    }
}
