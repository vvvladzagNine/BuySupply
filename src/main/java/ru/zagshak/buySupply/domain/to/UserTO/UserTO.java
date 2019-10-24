package ru.zagshak.buySupply.domain.to.UserTO;

import org.hibernate.annotations.BatchSize;
import ru.zagshak.buySupply.domain.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserTO {

    private String name;

    private Integer id;

    private String email;

    private String ava;

    private String city;


    private Date registered;

    public UserTO() {
    }

    public UserTO(User u) {
        this.name=u.getName();
        this.id = u.getId();
        this.email =u.getEmail();
        this.ava = u.getAva();
        this.city = u.getCity();
        this.registered = u.getRegistered();
    }

    public UserTO(String name,Integer id, String email, String ava, String city, Date registered) {
        this.id = id;
        this.name=name;
        this.email = email;
        this.ava = ava;
        this.city = city;
        this.registered = registered;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAva() {
        return ava;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

}
