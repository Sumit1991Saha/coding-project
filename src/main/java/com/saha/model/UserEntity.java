package com.saha.model;

import com.saha.rest.model.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    @Length(min=2, max=255)
    private String firstName;

    @Column(name = "lastname")
    @Length(min=2, max=255)
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "userEntity")
    private CalendarEntity calendarEntity;

    public UserEntity(){
    }
 
    public UserEntity(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CalendarEntity getCalendarEntity() {
        return calendarEntity;
    }

    public void setCalendarEntity(CalendarEntity calendarEntity) {
        this.calendarEntity = calendarEntity;
    }

    public User toDTO() {
        User user = new User();
        user.setId(this.getId());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        return user;
    }

    public void fromDTO(User user) {
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        CalendarEntity calendarEntity = new CalendarEntity();
        this.setCalendarEntity(calendarEntity);
        calendarEntity.setUserEntity(this);
    }
}
