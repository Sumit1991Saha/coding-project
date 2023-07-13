package com.saha.rest.resources.impl;

import com.saha.model.UserAvailableSlotEntity;
import com.saha.model.UserEntity;
import com.saha.persistence.PersistenceManager;
import com.saha.rest.resources.*;
import com.saha.rest.model.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.*;

import com.saha.rest.model.User;
import com.saha.rest.model.UserAvailabilitySlot;

import java.util.List;
import com.saha.rest.resources.NotFoundException;

import java.io.InputStream;
import java.util.stream.Collectors;

import com.saha.service.CrudService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2023-07-13T15:53:38.488+05:30[Asia/Kolkata]")
public class FetchOverlapApiServiceImpl extends FetchOverlapApiService {

    private EntityManager em;
    private EntityTransaction tx;
    private CrudService<UserEntity> userCrudService;
    private CrudService<UserAvailableSlotEntity> availableSlotCrudService;
    private String schemaName;

    public FetchOverlapApiServiceImpl() {
        em = PersistenceManager.getEntityManager();
        tx = em.getTransaction();
        userCrudService = new CrudService<>(UserEntity.class, em);
        availableSlotCrudService = new CrudService<>(UserAvailableSlotEntity.class, em);
        schemaName = "openapi";
    }
    @Override
    public Response fetchUserAvailabilityOverlap(List<User> users, SecurityContext securityContext) throws NotFoundException {
        if (users == null || users.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Set<Long> userIds = users.stream().map(user -> user.getId()).collect(Collectors.toSet());
        try {
            tx.begin();
            List<UserEntity> userEntities = userCrudService.findAll();
            tx.commit();

            Set<Long> validCalendarIds = new HashSet<>();
            for (UserEntity entity : userEntities) {
                if (userIds.contains(entity.getId())) {
                    validCalendarIds.add(entity.getCalendarEntity().getId());
                }
            }

            List<UserAvailabilitySlot> userAvailabilitySlots = new ArrayList<>();
            tx.begin();
            List<UserAvailableSlotEntity> availableSlotEntities = availableSlotCrudService.findAll();
            for (UserAvailableSlotEntity entity : availableSlotEntities) {
                long calendarId = entity.getCalendarEntity().getId();
                if (validCalendarIds.contains(calendarId)) {
                    userAvailabilitySlots.add(entity.toDTO());
                }
            }
            tx.commit();
            int numberOfUsers = validCalendarIds.size();
            List<AvailabilitySlot> availabilitySlots = computeOverLap(userAvailabilitySlots, numberOfUsers);
            return Response.ok(availabilitySlots).build();
        } catch (Exception e) {
            tx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    private List<AvailabilitySlot> computeOverLap(List<UserAvailabilitySlot> userAvailabilitySlots, int numberOfUsers) {
        List<Pair> timeSlots = new ArrayList<>(2*userAvailabilitySlots.size());
        for (UserAvailabilitySlot slot : userAvailabilitySlots) {
            Pair startTuple = new Pair(slot.getStartTime(), "S");
            Pair endTuple = new Pair(slot.getEndTime(), "E");
            timeSlots.add(startTuple);
            timeSlots.add(endTuple);
        }

        Comparator<Pair> comparator = new Comparator<Pair>() {
            @Override
            public int compare(Pair tuple1, Pair tuple2) {
                return tuple1.getDateTime().compareTo(tuple2.getDateTime());
            }
        };
        Collections.sort(timeSlots, comparator);

        List<AvailabilitySlot> overlapSlots = new ArrayList<>();
        int counter = 0;
        for (int i =0; i < timeSlots.size(); ++i) {
            Pair timeSlot = timeSlots.get(i);
            if (timeSlot.startOrEndNotation.equals("S")) {
                counter++;
            } else if (timeSlot.startOrEndNotation.equals("E")) {
                counter--;
            }
            if (counter == numberOfUsers) {
                AvailabilitySlot availableOverlapSlot = new AvailabilitySlot();
                availableOverlapSlot.setStartTime(timeSlot.getDateTime());
                i++;
                timeSlot = timeSlots.get(i);
                availableOverlapSlot.setEndTime(timeSlot.getDateTime());
                counter--;
                overlapSlots.add(availableOverlapSlot);
            }
        }
        return overlapSlots;
    }

    class Pair {
        OffsetDateTime dateTime;
        String startOrEndNotation; // Value for this is either S or E ie for Start and End

        Pair(OffsetDateTime dateTime, String startOrEndNotation) {
            this.dateTime = dateTime;
            this.startOrEndNotation = startOrEndNotation;
        }

        public OffsetDateTime getDateTime() {
            return dateTime;
        }

        public String getStartOrEndNotation() {
            return startOrEndNotation;
        }
    }
}
