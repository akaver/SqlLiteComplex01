package com.akaver.sqllitecomplex01;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private UOW uow;
    private PersonsAdapter personsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        uow = new UOW(getApplicationContext());

        // start with fresh database
        uow.DropCreateDatabase();
        uow.SeedData();


        displayListView();
    }


    private void displayListView() {
        personsAdapter = new PersonsAdapter(this, uow.personRepo.getCursorAll(), uow);

        ListView listView = (ListView) findViewById(R.id.personsListView);

        // Assign adapter to ListView
        // listview will iterate over adapter, and get filled subview for every row
        listView.setAdapter(personsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // Get the id
                String dbid =
                        cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                Toast.makeText(getApplicationContext(),
                        dbid, Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_person) {
            return true;
        }
        if (id == R.id.action_add_contact) {
            return true;
        }
        if (id == R.id.action_add_contacttype) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
