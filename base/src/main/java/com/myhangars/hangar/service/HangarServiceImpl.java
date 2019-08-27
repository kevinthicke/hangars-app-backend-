package com.myhangars.hangar.service;

import com.myhangars.exception.GenericException;
import com.myhangars.hangar.dao.HangarDao;
import com.myhangars.hangar.model.Hangar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HangarServiceImpl implements HangarService{

    @Autowired
    private HangarDao hangarDao;

    @Override
    public Page< Hangar > getAll(Pageable pageable) {

        Page<Hangar> hangars = this.hangarDao.findAll(pageable);

        if(hangars.isEmpty())
            throw new GenericException.NotFound();

        return hangars;
    }

    @Override
    public List getAllNames() {
        return this.getAll(null)
                .stream()
                .map(Hangar::getName)
                .collect(Collectors.toList());
    }


    @Override
    public Hangar getById(long id) {

        return this.hangarDao
                .findById(id)
                .orElseThrow(() -> new GenericException.NotFound(id));
    }

    @Override
    public List<Hangar> getByName(String name) {

        List<Hangar> hangars = this.hangarDao
                .findByName(name);

        if(hangars.isEmpty())
            throw new GenericException.NotFound();
        return hangars;
    }

    @Override
    public Hangar getFirstByName(String name) {
        return this.getByName(name).get(0);
    }

    @Override
    public Hangar insert(Hangar hangar) {

        String hangarName = hangar.getName();

        if(this.hangarDao.findByName(hangarName) == null)
            throw new GenericException.AlreadyExists(hangarName);

        return this.hangarDao.save(hangar);
    }

    @Override
    public Hangar update(long id, Hangar hangar) {

        Hangar _hangar = this.getById(id);

        _hangar.setName(hangar.getName());
        _hangar.setLocation(hangar.getLocation());
        _hangar.setEmail(hangar.getEmail());
        _hangar.setOwner(hangar.getOwner());

        _hangar.setPhone(hangar.getPhone());

        return this.hangarDao.save(_hangar);
    }

    @Override
    public Hangar safeDeleteById(long id) {

        Hangar hangar = this.getById(id);
        hangar.setState(false);

        return this.hangarDao.save(hangar);
    }
}
