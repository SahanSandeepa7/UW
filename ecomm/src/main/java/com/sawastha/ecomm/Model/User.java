package com.sawastha.ecomm.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int Id;

    @Column(name="email")
    @Email(message ="Enter a valid email")
    @NotEmpty(message = "Please provide a email")
    private String Email;

    @JsonIgnore
    @Column(name = "address")
    private String address;

    @JsonIgnore
    @Column(name = "phone")
    private String phone;

    @Column(name="username")
    @NotEmpty(message = "Please provide a username")
    private String UserName;

    @JsonIgnore
    @Column(name="password")
    @Length(min = 8, message ="The password should be at least 8 characters")
    @NotEmpty(message = "Please provide a password")
    private String Password;

    @Transient
    @JsonIgnore
    private String confirmPassword;

    @Column(name = "role_id")
    private int RoleId;

    @Column(name = "location_id")
    private int LocId;

    @Column(name = "image")
    @NotEmpty(message = "Please provide a image")
    private String Image;

    @Column(name="lat")
    private String Lat;

    @Column(name="lng")
    private String Lng;

    @JsonIgnore
    @Column(name = "enabled")
    private Boolean enabled;

    @JsonIgnore
    @Column(name = "created_at")
    private Timestamp created;

    @JsonIgnore
    @Column(name = "updated_at")
    private Timestamp updated;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        this.Id = id;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getUserName() {
        return UserName;
    }
    public void setUserName(String username) {
        this.UserName = username;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        this.Password = password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public int getRoleId() {
        return RoleId;
    }
    public void setRoleId(int roleId) {
        this.RoleId = roleId;
    }
    public int getLocId() {
        return LocId;
    }
    public void setLocId(int locId) {
        this.LocId = locId;
    }
    public String getImage() {
        return Image;
    }
    public void setImage(String image) {
        this.Image = image;
    }
    public String getLAT() {
        return Lat;
    }
    public void setLAT(String lat) {
        this.Lat = lat;
    }
    public String getLng() {
        return Lng;
    }
    public void setLng(String lng) {
        this.Lng = lng;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public Timestamp getCreated() {
        return created;
    }
    public void setCreated(Timestamp created) {
        this.created = created;
    }
    public Timestamp getUpdated() {
        return updated;
    }
    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }


    @Override
    public String toString() {
        return "User [Id=" + Id + ", Email=" + Email + ", Name=" + UserName + ", Password=" + Password + "]";
    }


}
