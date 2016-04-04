package com.akaver.sqllitecomplex01;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by akaver on 03/04/16.
 */
public class PersonRepo extends Repo<Person> {

    public PersonRepo(SQLiteDatabase database, String tableName, String[] allColumns){
        super(database, tableName, allColumns);
    };

    @Override
    public Person cursorToEntity(Cursor cursor) {
        Person person = new Person();
        person.setId(cursor.getLong(0));
        person.setFirstName(cursor.getString(1));
        person.setLastName(cursor.getString(2));
        return person;
    }

    @Override
    public ContentValues entityToContentValues(Person person) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MySQLiteHelper.COLUMN_PERSONS_FIRSTNAME, person.getFirstName());
        contentValues.put(MySQLiteHelper.COLUMN_PERSONS_LASTNAME, person.getLastName());

        return contentValues;
    }


}
