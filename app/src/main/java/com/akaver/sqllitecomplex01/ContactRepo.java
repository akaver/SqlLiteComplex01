package com.akaver.sqllitecomplex01;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akaver on 03/04/16.
 */
public class ContactRepo extends Repo<Contact> {
    public ContactRepo(SQLiteDatabase database, String tableName, String[] allColumns) {
        super(database, tableName, allColumns);
    }

    @Override
    public Contact cursorToEntity(Cursor cursor) {
        Contact contact = new Contact();
        contact.setId(cursor.getLong(0));
        contact.setValue(cursor.getString(1));
        contact.setPersonId(cursor.getLong(2));
        contact.setContactTypeId(cursor.getLong(3));
        return contact;
    }

    @Override
    public ContentValues entityToContentValues(Contact contact) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MySQLiteHelper.COLUMN_CONTACTS_VALUE, contact.getValue());
        contentValues.put(MySQLiteHelper.COLUMN_CONTACTS_PERSONID, contact.getPersonId());
        contentValues.put(MySQLiteHelper.COLUMN_CONTACTS_CONTACTTYPEID, contact.getContactTypeId());

        return contentValues;
    }

    public List<Contact> getForPerson(long personId){
        List<Contact> listOfEntity = new ArrayList<Contact>();

        Cursor cursor = getDatabase().query(getTableName(),
                getAllColumns(), "personId = " + personId, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contact entity = cursorToEntity(cursor);
            listOfEntity.add(entity);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return listOfEntity;
    }
}
