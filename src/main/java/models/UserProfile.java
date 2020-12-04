package models;

import tasks.Search;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
@Table(name = "UserProfile")
@NamedQueries(
        {
                @NamedQuery(
                        name = "getAllUsers",
                        query = "SELECT l FROM UserProfile l"
                )
/*                @NamedQuery(
                        name = "updateUser",
                        query = "UPDATE UserProfile"
                )*/
        })

public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "country")
    private String country;

    @Column(name = "interests")
    private String interests;

    @Column(name = "followingChannels")
    private String followingChannels;

    /**
     * Constructor.
     *
     * @param userId            the id of the user
     * @param name              the user's name
     * @param email             the user's email
     * @param phone             the user's phone number
     * @param gender            the user's gender
     * @param age               the user's age
     * @param country           the country of the user
     * @param interests         the user's interests
     * @param followingChannels The channels that have been followed.
     */
    public UserProfile(String userId, String name, String email, String phone, String gender, int age, String country,
                       ArrayList<String> interests, ArrayList<InfluencerProfile> followingChannels) {

        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.country = country;
        this.name = name;
        StringBuffer sb1 = new StringBuffer();
        for (String s : interests) {
            sb1.append(s);
            sb1.append(",");
        }
        this.interests = sb1.toString();
        StringBuffer sb2 = new StringBuffer();
        for (InfluencerProfile i : followingChannels) {
            sb2.append(i.channelId);
            sb2.append(",");
        }
        this.followingChannels = sb2.toString();
    }

    public UserProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserProfile() {
    }

/*    public String getUserId() {
        return userId;
    }*/

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

/*    public String getPhone() {
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
    }*/

    public ArrayList<String> getInterests() {
        String str[] = interests.split(",");
        ArrayList<String> interestsList = new ArrayList<>();
        for (String s : str) {
            interestsList.add(s);
        }
        return interestsList;
    }

    public ArrayList<InfluencerProfile> getFollowingChannels() {
        String str[] = followingChannels.split(",");
        ArrayList<String> channelIds = new ArrayList<String>(Arrays.asList(str));
        ArrayList<InfluencerProfile> followingChannelsList = new ArrayList<>();
        Search search;
        InfluencerProfile curInfluencer;
        for (String id : channelIds) {
            if (!id.equals("")) {
                search = new Search(id);
                curInfluencer = search.getInfluencerProfileByID();
                followingChannelsList.add(curInfluencer);
            }
        }
        return followingChannelsList;
    }

/*    public String getImageUrl() {
        return imageUrl;
    }*/
}
