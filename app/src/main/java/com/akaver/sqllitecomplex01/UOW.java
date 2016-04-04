package com.akaver.sqllitecomplex01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by akaver on 03/04/16.
 */
public class UOW {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private final Context context;

    public PersonRepo personRepo;
    public ContactTypeRepo contactTypeRepo;
    public ContactRepo contactRepo;

    public UOW(Context context){
        this.context = context;

        dbHelper = new MySQLiteHelper(context);
        database = dbHelper.getWritableDatabase();

        personRepo = new PersonRepo(database, dbHelper.TABLE_PERSONS, dbHelper.ALLCOLUMNS_PERSONS);
        contactTypeRepo = new ContactTypeRepo(database, dbHelper.TABLE_CONTACTTYPES, dbHelper.ALLCOLUMNS_CONTACTTYPES);
        contactRepo = new ContactRepo(database, dbHelper.TABLE_CONTACTS, dbHelper.ALLCOLUMNS_CONTACTS);
    }

    public void DropCreateDatabase(){
        dbHelper.dropCreateDatabase(database);
    }

    public void SeedData(){
        ContactType contactTypeSkype = contactTypeRepo.add(new ContactType("Skype"));
        ContactType contactTypeEmail = contactTypeRepo.add(new ContactType("Email"));

        Person person1 = personRepo.add(new Person("Andres", "Käver"));
        Person person2 = personRepo.add(new Person("Mait", "Poska"));

        contactRepo.add(new Contact("akaver",person1.getId(), contactTypeSkype.getId()));
        contactRepo.add(new Contact("akaver@itcollege.ee",person1.getId(), contactTypeEmail.getId()));

        contactRepo.add(new Contact("minamait",person2.getId(), contactTypeSkype.getId()));
        contactRepo.add(new Contact("mait.poska@itcollege.ee",person2.getId(), contactTypeEmail.getId()));
    }

}
