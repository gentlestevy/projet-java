package userModels;

import java.util.Date;

public class Client extends User {
    private Genders gender;
    private String address;



    public  Genders getGender() {
        return gender;
    }

    public void setGender(Genders gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void printInfos(){
        System.out.println(getId());
        System.out.println(getFirstname());
        System.out.println(getLastname());
        System.out.println(getAddress());
        System.out.println(getGender().toString());
        System.out.println(getDateCreation().toString());
        System.out.println(getEmail());

    }

}
