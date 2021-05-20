package com.example.appmenu;

import android.Manifest;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {
    ListView listView;
    ContentResolver contentResolver;
    Button button, buttonReadStorage, buttonGetBookmarks;
    ArrayAdapter adapter;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        button = (Button) findViewById(R.id.buttonDisplay);
        buttonReadStorage = (Button) findViewById(R.id.buttonReadStorage);
        buttonGetBookmarks = (Button) findViewById(R.id.buttonBookmarks);
        listView = (ListView) findViewById(R.id.listViewContacts);
        // button Display set On Click
        list = new ArrayList<String>();
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                list.removeAll(list);
                if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 100);
                }
                contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID}, null, null, null);
                // Show Contacts on listView

                while (cursor.moveToNext()) {
                    long id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor cursor1 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER}, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =? ", new String[]{String.valueOf(id)}, null);
                    String sdt = "";
                    while (cursor1.moveToNext()) {
                        sdt += cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) + ".\n    ";

                    }
                    list.add(cursor.getString(0) + "\nSĐT: " + sdt);

                }
                adapter = new ArrayAdapter(ContactsActivity.this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(adapter);
            }
        });
        //
        buttonReadStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.removeAll(list);
                String[] projection = new String[]{"_display_name", "date_added", "mime_type"};
                CursorLoader loader = new CursorLoader(ContactsActivity.this, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, (String) null, null, (String) null);
                Cursor cursor = loader.loadInBackground();
                cursor.moveToFirst();
                String str = "";
                while (!cursor.isAfterLast()) {
                    for (int i = 0; i < cursor.getColumnCount(); ++i) {
                        str += cursor.getString(i) + ".\n";
                    }
                }
                str += "\n";
                cursor.moveToNext();
                list.add(str);
                adapter = new ArrayAdapter(ContactsActivity.this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(adapter);
            }
        });
        //
        buttonGetBookmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri BOOKMARKS_URI = Uri.parse("content://browser/bookmarks");
                String[] projection = new String[]{"title", "url"};
                Cursor c = getContentResolver().query(BOOKMARKS_URI, projection, (String)null, (String[])null, (String)null);

                c.moveToFirst();
                String s = "";
                int titleIndex = c.getColumnIndex("title");
                int urlIndex = c.getColumnIndex("url");

                while(!c.isAfterLast()) {
                    s = s + c.getString(titleIndex) + " - " + c.getString(urlIndex);
                    c.moveToNext();
                }

                c.close();
                Toast.makeText(ContactsActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

    }

}