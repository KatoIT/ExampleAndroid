package com.example.appmenu;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    EditText editTextSearch;
//    Receiver receiver = new Receiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Ánh xạ
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        listView = (ListView) findViewById(R.id.ListViewBaiTap);
        // ArrayList
        ArrayList<BaiTap> arrayListBaiTap = new ArrayList<BaiTap>();
        arrayListBaiTap.add(new BaiTap("Sum", "14/1/2021")); // Sum
        arrayListBaiTap.add(new BaiTap("ListView", "14/1/2021")); // ListView
        arrayListBaiTap.add(new BaiTap("CustomsListView_ArrayAdapter", "14/1/2021")); // CustomListViewArrayAdapter & SinhVien & ArrayAdapterSinhVien.java
        arrayListBaiTap.add(new BaiTap("CustomsListView_BaseAdapter", "14/1/2021")); // Main & BaiTap & MainActivityBaseAdapter.java
        arrayListBaiTap.add(new BaiTap("Intent Activity", "14/1/2021")); // Home & Next
        arrayListBaiTap.add(new BaiTap("Intent Activity2", "2/2/2021")); // First & Second
        arrayListBaiTap.add(new BaiTap("SQLite", "23/2/2021")); // Sqlite & DataBase.java
        arrayListBaiTap.add(new BaiTap("Contacts", "2/3/2021")); // Sqlite & DataBase.java
        arrayListBaiTap.add(new BaiTap("Service", "9/3/2021")); // Sqlite & DataBase.java
        arrayListBaiTap.add(new BaiTap("Service Weather", "9/3/2021")); // Sqlite & DataBase.java
//        arrayListBaiTap.add(new BaiTap("Intent","14/1/2021"));

        MainActivityBaseAdapter baseAdapter = new MainActivityBaseAdapter(arrayListBaiTap, MainActivity.this);
        listView.setAdapter(baseAdapter);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                baseAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                }
                switch (position) {
                    case 0: {
                        Intent intent = new Intent(MainActivity.this, SumActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: {
                        Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 2: {
                        Intent intent = new Intent(MainActivity.this, CustomListViewArrayAdapterActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 4: {
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 5: {
                        Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 6: {
                        Intent intent = new Intent(MainActivity.this, SqliteActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 7: {
                        Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 8: {
                        Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 9: {
                        Intent intent = new Intent(MainActivity.this, Service2Activity.class);
                        startActivity(intent);
                        break;
                    }

                    default: {
                        Toast.makeText(MainActivity.this, "Chưa được cài đặt", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
//  Dang ky = code
//    @Override
//    protected void onStart() {
//        super.onStart();
//        IntentFilter filter = new IntentFilter();
//        registerReceiver(receiver, filter);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        unregisterReceiver(receiver);
//    }
}