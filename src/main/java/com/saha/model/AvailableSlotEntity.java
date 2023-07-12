package com.saha.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "available_slot")
public class AvailableSlotEntity {

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
        AvailableSlotEntity that = (AvailableSlotEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(calendarEntity, that.calendarEntity) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calendarEntity, startTime, endTime);
    }
}
