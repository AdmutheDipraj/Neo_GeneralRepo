package com.neosoft.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class Responcedto {

    private Userdto userdto;
    private Policydto policydto;

    public Userdto getUserdto() {
        return userdto;
    }

    public void setUserdto(Userdto userdto) {
        this.userdto = userdto;
    }

    public Policydto getPolicydto() {
        return policydto;
    }

    public void setPolicydto(Policydto policydto) {
        this.policydto = policydto;
    }
}
