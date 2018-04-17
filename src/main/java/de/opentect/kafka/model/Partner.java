package de.opentect.kafka.model;

import java.util.Date;

public class Partner {

    private String partnerUUID;
    private String firstName;
    private String lastName;
    private Date lastChanged;

    public String getPartnerUUID() {
        return partnerUUID;
    }

    public void setPartnerUUID(String partnerUUID) {
        this.partnerUUID = partnerUUID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(Date lastChanged) {
        this.lastChanged = lastChanged;
    }


}

