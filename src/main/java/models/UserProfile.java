package models;

import java.util.ArrayList;

public class UserProfile {

    private final String userId;
    private final String name;
    private final String email;
    private final String phone;
    private final String gender;
    private final int age;
    private final String country;
    private final ArrayList<String> interests;

    /**
     * Constructor.
     *
     * @param userId
     * the id of the user
     * @param name
     * the user's name
     * @param email
     * the user's email
     * @param phone
     * the user's phone number
     * @param gender
     * the user's gender
     * @param age
     * the user's age
     * @param country
     * the country of the user
     * @param interests
     * the user's interests
     */
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
