package com.example.appmenu;

public class SinhVien {
    public String fullName;
    public int birthYear;
    public int id;

    public SinhVien(String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public SinhVien(String fullName, int birthYear, int id) {
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
