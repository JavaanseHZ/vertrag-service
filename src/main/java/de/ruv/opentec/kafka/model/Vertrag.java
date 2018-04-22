package de.ruv.opentec.kafka.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vertrag {

    @Id
    private long id;
    private Partner partner;
    private String sparte;
    private double beitrag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public String getSparte() {
        return sparte;
    }

    public void setSparte(String sparte) {
        this.sparte = sparte;
    }

    public double getBeitrag() {
        return beitrag;
    }

    public void setBeitrag(double beitrag) {
        this.beitrag = beitrag;
    }

}

