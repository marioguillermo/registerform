package com.example.registerform.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class UserJava implements Parcelable {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String birthday;
    private String gender;

    public UserJava() {
    }

    public UserJava(String name, String lastName, String email, String password, String birthDay, String gender) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthDay;
        this.gender = gender;
    }

    protected UserJava(Parcel in) {
        name = in.readString();
        lastName = in.readString();
        email = in.readString();
        password = in.readString();
        birthday = in.readString();
        gender = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(birthday);
        dest.writeString(gender);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserJava> CREATOR = new Creator<UserJava>() {
        @Override
        public UserJava createFromParcel(Parcel in) {
            return new UserJava(in);
        }

        @Override
        public UserJava[] newArray(int size) {
            return new UserJava[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
