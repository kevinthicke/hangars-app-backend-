package com.myhangars.hangar.dao;

import com.myhangars.hangar.model.Hangar;
import com.myhangars.hangar.repository.HangarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HangarDaoImpl implements HangarDao {

    @Autowired
    private HangarRepository hangarRepository;

    @Override
    public Page< Hangar > findAll(Pageable pageable) {
        return this.hangarRepository.findAllWithTrueState(pageable);
    }

    @Override
    public List<Hangar> findByName(String name) {
        return this.hangarRepository.findByNameWithTrueState("%" + name + "%");
    }

    /*
    @Override
    public Optional<Hangar> findExactlyByName(String name) {
        return this.hangarRepository.findExactlyByNameWithTrueState(name);
    }
    */

    @Override
    public Optional<Hangar> findById(long id) {
        return this.hangarRepository.findByIdWithTrueState(id);
    }

    @Override
    public Hangar save(Hangar hangar) {
        return this.hangarRepository.save(hangar);
    }

    @Override
    public Hangar update(Hangar hangar) {
        return this.hangarRepository.save(hangar);
    }
}
