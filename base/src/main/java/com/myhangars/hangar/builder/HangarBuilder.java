package com.myhangars.hangar.builder;

import com.myhangars.hangar.dto.HangarDto;
import com.myhangars.hangar.model.Hangar;

public class HangarBuilder {

    private Hangar hangar;

    public HangarBuilder(HangarDto hangarDto) {
        hangar = new Hangar();

        this.hangar.setName(hangarDto.getName());
        this.hangar.setLocation(hangarDto.getLocation());
        this.hangar.setOwner(hangarDto.getOwner());
        this.hangar.setEmail(hangarDto.getEmail());
        this.hangar.setPhone(hangarDto.getPhone());
    }

    public Hangar getHangar() {
        return hangar;
    }

}
