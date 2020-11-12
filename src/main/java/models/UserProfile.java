package models;

import java.util.ArrayList;

public class UserProfile {

    private String userId;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private int age;
    private String country;
    private ArrayList<String> interests;

    public UserProfile(String userId, String name, String email, String phone, String gender, int age, String country, ArrayList<String> interests) {
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.country = country;
        this.interests = interests;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }
}
