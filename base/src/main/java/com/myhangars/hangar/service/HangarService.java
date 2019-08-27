package com.myhangars.hangar.service;

import com.myhangars.hangar.model.Hangar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HangarService {
    public Page< Hangar > getAll(Pageable pageable);
    public List<String> getAllNames();
    public Hangar getById(long id);
    public List<Hangar> getByName(String name);
    public Hangar getFirstByName(String name);
    public Hangar insert(Hangar hangar);
    public Hangar update(long id, Hangar hangar);
    public Hangar safeDeleteById(long id);
}
