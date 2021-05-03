package userModels;

import java.sql.Date;
import java.time.LocalDate;

public abstract   class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private LocalDate dateCreation;


    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public int getId() {
        return id;
    }

    public void setDateCreation(LocalDate date) {
        this.dateCreation = date;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

}
