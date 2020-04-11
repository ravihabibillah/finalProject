package com.example.finalproject.Model;

public class LoginModel {
    private String phone, name, email, password, location, gender, birth;

    public LoginModel(){

    }

    public LoginModel(String phone, String name, String email, String password, String location, String gender, String birth) {
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.gender = gender;
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
