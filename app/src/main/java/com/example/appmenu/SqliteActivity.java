package com.example.appmenu;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SqliteActivity extends AppCompatActivity {
    EditText editTextName, editTextID, editTextNamSinh;
    Button buttonInsert, buttonUpdate, buttonDelete, buttonShow;
    ListView listViewShow;
    DataBase dataBase = new DataBase(SqliteActivity.this);
    ArrayList<SinhVien> arrayListSinhVien = new ArrayList<SinhVien>();

    @Override
    protected void onStart() {
        super.onStart();
        dataBase.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dataBase.closeDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextName = (EditText) findViewById(R.id.editTextHoTen);
        editTextNamSinh = (EditText) findViewById(R.id.editTextNamSinh);
        buttonInsert = (Button) findViewById(R.id.buttonInsert);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonShow = (Button) findViewById(R.id.buttonShow);
        listViewShow = (ListView) findViewById(R.id.listViewShow);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resultAdd = dataBase.Insert(Integer.parseInt(editTextID.getText().toString()), editTextName.getText().toString(), Integer.parseInt(editTextNamSinh.getText().toString()));
                if (resultAdd == -1) {
                    Toast.makeText(SqliteActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }else{
                    refeshActivity();
                }
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resultUpdate = dataBase.update(Integer.parseInt(editTextID.getText().toString()), editTextName.getText().toString(), Integer.parseInt(editTextNamSinh.getText().toString()));
                if (resultUpdate == 0) {
                    Toast.makeText(SqliteActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }else {
                    if (resultUpdate != 1){
                        Toast.makeText(SqliteActivity.this, "Trung", Toast.LENGTH_SHORT).show();
                    }else{
                        refeshActivity();
                    }
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resultDelete = dataBase.delete(Integer.parseInt(editTextID.getText().toString()));
                if (resultDelete == 0) {
                    Toast.makeText(SqliteActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }else{
                    refeshActivity();
                }
            }
        });
        refeshActivity();
        ArrayAdapterSinhVien arrayAdapterSV = new ArrayAdapterSinhVien(SqliteActivity.this, R.layout.item_sinh_vien, arrayListSinhVien);
        listViewShow.setAdapter(arrayAdapterSV);

        listViewShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editTextID.setText(arrayAdapterSV.getItem(position).getId()+"");
                editTextName.setText(arrayAdapterSV.getItem(position).getFullName());
                editTextNamSinh.setText(arrayAdapterSV.getItem(position).getBirthYear()+"");
            }
        });
    }
    public void refeshActivity(){
        arrayListSinhVien.removeAll(arrayListSinhVien);
        try {
            // SELECT data
            // Text View Công
            Cursor cursor = dataBase.GetData("SELECT * FROM SinhVien");
            while(cursor.moveToNext()){
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                int namsinh = Integer.parseInt(cursor.getString(2));
                arrayListSinhVien.add(new SinhVien(name, namsinh, id));
            }
        } catch (NumberFormatException e) {
            Toast.makeText(SqliteActivity.this, "ERORR: Truy xuất", Toast.LENGTH_SHORT).show();
        }
    }
}