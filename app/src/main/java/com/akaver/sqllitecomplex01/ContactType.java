package com.akaver.sqllitecomplex01;

/**
 * Created by akaver on 03/04/16.
 */
public class ContactType implements IEntity {
    private long id;
    private String value;

    public ContactType(){

    }

    public ContactType(String value){
        setValue(value);
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
    @Override
    public String toString() {
        return value;
    }
}
