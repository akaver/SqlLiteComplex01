package com.akaver.sqllitecomplex01;

/**
 * Created by akaver on 03/04/16.
 */
public class Contact implements IEntity{
    private long id;
    private String value;
    private long personId;
    private long contactTypeId;

    public Contact(){

    }

    public Contact(String value, long personId, long contactTypeId){
        setValue(value);
        setPersonId(personId);
        setContactTypeId(contactTypeId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value.trim();
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public long getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(long contactTypeId) {
        this.contactTypeId = contactTypeId;
    }


    @Override
    public String toString() {
        return value;
    }
}
