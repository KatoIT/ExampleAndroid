package com.example.appmenu;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBase extends SQLiteOpenHelper {
    private static final String DATABASENAME = "note.db";
    private static final int VERSION = 1;
    private SQLiteDatabase sqLiteDB;


    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBase(Context context) {
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String a = "CREATE TABLE IF NOT EXISTS SinhVien(Id INTEGER PRIMARY KEY, FullName TEXT NOT NULL, NamSinh INTEGER NOT NULL)";
        db.execSQL(a);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Truy vấn có trả kết quả: CREATE, INSERT, UPDATE, DELETE,...
//    public void QueryData(String str) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        sqLiteDatabase.execSQL(str);
//    }

    // Truy vấn có trả kết quả: SELECT
    public Cursor GetData(String str) {
        sqLiteDB = getReadableDatabase();
        return sqLiteDB.rawQuery(str, null);
    }

    //

    public void openDB() {
        sqLiteDB = getWritableDatabase();
    }

    public void closeDB() {
        if (sqLiteDB != null && sqLiteDB.isOpen()) {
            sqLiteDB.close();
        }
    }

    public long Insert(int id, String name, int namsinh) {
        ContentValues values = new ContentValues();
        values.put("Id", id);
        values.put("FullName", name);
        values.put("NamSinh", namsinh);
        return sqLiteDB.insert("SinhVien", null, values);
    }
//    long resultAdd = DataBase.Insert("key", "values");
//    if(resultAdd == -1) { "ERROR"}else{"OK"}

    public long update(int id, String name, int namsinh) {
        ContentValues values = new ContentValues();
        values.put("Id", id);
        values.put("FullName", name);
        values.put("NamSinh", namsinh);
        String where = "Id" + " = " + id;
        return sqLiteDB.update("SinhVien", values, where, null);
    }

    // 0 = ERROR // 1 = ok // else = Trung id
    public long delete(int id) {
        String where = "Id" + " = " + id;
        return sqLiteDB.delete("SinhVien", where, null);
    }
    // 0 = error // 1 = ok


}



/*
* // Tạo bảng 'IF NOT'
        database.QueryData("CREATE TABLE IF NOT EXISTS ChamCong(Id DATE PRIMARY KEY, GioLam DOUBLE, TangCa DOUBLE, CN BOOLEAN)");
        // Insert Data
        try {
//            database.QueryData("INSERT INTO ChamCong VALUES('30/12/2021', 8, 0.0, 'TRUE' )");
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        *
        * try {
            // SELECT data
            // Text View Công
            Cursor cursor = database.GetData("SELECT COUNT(Id) FROM ChamCong WHERE strftime('%Y',ID) = strftime('%Y',date('now'))  AND strftime('%m',ID) = '" + month + "' AND GioLam = 8");
            cursor.moveToNext();
            } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "ERORR: Truy xuất", Toast.LENGTH_SHORT).show();
        }
* */