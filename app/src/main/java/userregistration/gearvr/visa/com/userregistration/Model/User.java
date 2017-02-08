package userregistration.gearvr.visa.com.userregistration.Model;

import java.io.Serializable;

/**
 * Created by svuddara on 2/6/17.
 */

public class User implements Serializable {

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }


    public String[] getReceipt_urls() {
        return receipt_urls;
    }


    public String getProfile_image() {
        return profile_image;
    }


    public boolean isTook_photo() {
        return took_photo;
    }


    public String getExperiences_choice() {
        return experiences_choice;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setReceipt_urls(String[] receipt_urls) {
        this.receipt_urls = receipt_urls;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public void setTook_photo(boolean took_photo) {
        this.took_photo = took_photo;
    }

    public void setExperiences_choice(String experiences_choice) {
        this.experiences_choice = experiences_choice;
    }

    private String first_name;
    private String last_name;
    private String email;
    private String[] receipt_urls;
    private String profile_image;
    private boolean took_photo;
    private String experiences_choice;

    private User(UserBuilder builder){
        this.first_name = builder.first_name;
        this.last_name = builder.last_name;
        this.email = builder.email;
        this.receipt_urls = builder.receipt_urls;
        this.profile_image = builder.profile_image;
        this.took_photo = builder.took_photo;
        this.experiences_choice = builder.experiences_choice;
    }

    public static class UserBuilder{
        private String first_name;
        private String last_name;
        private String email;
        private String[] receipt_urls;
        private String profile_image;
        private boolean took_photo;
        private String experiences_choice;

        public UserBuilder(){

            }

        public UserBuilder setFirstName(String first_name){
            this.first_name = first_name;
            return this;
        }

        public UserBuilder setLastName(String last_name){
            this.last_name = last_name;
            return this;
        }

        public UserBuilder setEmail(String email){
            this.email  = email;
            return this;
        }

        public UserBuilder setProfileImage(String profile_image){
            this.profile_image = profile_image;
            return this;
        }

        public UserBuilder settookPhoto(boolean took_photo){
            this.took_photo = took_photo;
            return this;
        }

        public UserBuilder setExperiencesChoice(String experiences_choice){
            this.experiences_choice = experiences_choice;
            return this;
        }

        public UserBuilder setReceiptUrls(String[] receipt_urls){
            this.receipt_urls = receipt_urls;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

}
