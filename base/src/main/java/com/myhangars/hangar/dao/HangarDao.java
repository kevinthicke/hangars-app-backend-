package com.myhangars.hangar.dao;

import com.myhangars.hangar.model.Hangar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface HangarDao {
    public Page< Hangar > findAll(Pageable pageable);
    public List<Hangar> findByName(String name);
    //public Optional<Hangar> findExactlyByName(String name);
    public Optional<Hangar> findById(long id);
    public Hangar save(Hangar hangar);
    public Hangar update(Hangar hangar);
}
