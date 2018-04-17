package de.opentect.kafka.model;

import java.util.Date;

@Entity
public class Vertrag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String vertragUUID;
    private String sparte;
    private String beitrag;
    private Date lastChanged;

    public String getVertragUUID() {
        return vertragUUID;
    }

    public void setVertragUUID(String vertragUUID) {
        this.vertragUUID = vertragUUID;
    }

    public String getSparte() {
        return sparte;
    }

    public void setSparte(String sparte) {
        this.sparte = sparte;
    }

    public String getBeitrag() {
        return beitrag;
    }

    public void setBeitrag(String beitrag) {
        this.beitrag = beitrag;
    }

    public Date getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(Date lastChanged) {
        this.lastChanged = lastChanged;
    }


}


}

