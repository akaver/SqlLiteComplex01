package com.akaver.sqllitecomplex01;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by akaver on 03/04/16.
 */
public class ContactTypeRepo extends Repo<ContactType> {

    public ContactTypeRepo(SQLiteDatabase database, String tableName, String[] allColumns) {
        super(database, tableName, allColumns);
    }

    @Override
    public ContactType cursorToEntity(Cursor cursor) {
        ContactType contactType = new ContactType();
        contactType.setId(cursor.getLong(0));
        contactType.setValue(cursor.getString(1));
        return contactType;
    }

    @Override
    public ContentValues entityToContentValues(ContactType contactType) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteHelper.COLUMN_CONTACTTYPES_VALUE, contactType.getValue());
        return contentValues;
    }

}
