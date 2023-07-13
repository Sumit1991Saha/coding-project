package com.saha.model;

import com.saha.rest.model.User;
import com.saha.rest.model.UserAvailabilitySlot;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "available_slot")
public class UserAvailableSlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "calendar_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CalendarEntity calendarEntity;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CalendarEntity getCalendarEntity() {
        return calendarEntity;
    }

    public void setCalendarEntity(CalendarEntity calendarEntity) {
        this.calendarEntity = calendarEntity;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAvailableSlotEntity that = (UserAvailableSlotEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(calendarEntity, that.calendarEntity) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calendarEntity, startTime, endTime);
    }

    public UserAvailabilitySlot toDTO() {
        UserAvailabilitySlot userAvailabilitySlot = new UserAvailabilitySlot();
        userAvailabilitySlot.setId(this.getId());
        userAvailabilitySlot.setCalendarId(this.getCalendarEntity().getId());
        userAvailabilitySlot.setStartTime(this.getStartTime().toString());
        userAvailabilitySlot.setEndTime(this.getEndTime().toString());
        return userAvailabilitySlot;
    }

    public void fromDTO(UserAvailabilitySlot userAvailabilitySlot, long calendarId) throws ParseException {
        CalendarEntity calendarEntity = new CalendarEntity();
        calendarEntity.setId(calendarId);
        this.setCalendarEntity(calendarEntity);
        this.setStartTime(DateFormat.getInstance().parse(userAvailabilitySlot.getStartTime()));
        this.setEndTime(DateFormat.getInstance().parse(userAvailabilitySlot.getEndTime()));
    }
}
