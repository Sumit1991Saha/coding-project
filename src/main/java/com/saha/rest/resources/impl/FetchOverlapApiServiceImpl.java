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

            Map<Long, User> calendarIdVsUser = new HashMap<>();
            for (UserEntity entity : userEntities) {
                if (userIds.contains(entity.getId())) {
                    calendarIdVsUser.put(entity.getCalendarEntity().getId(), entity.toDTO());
                }
            }

            Map<User, List<UserAvailabilitySlot>> userVsAvailableSlots = new HashMap<>();
            tx.begin();
            List<UserAvailableSlotEntity> availableSlotEntities = availableSlotCrudService.findAll();
            for (UserAvailableSlotEntity entity : availableSlotEntities) {
                long calendarId = entity.getCalendarEntity().getId();
                User user = calendarIdVsUser.get(calendarId);
                if (userVsAvailableSlots.containsKey(user)) {
                    userVsAvailableSlots.get(user).add(entity.toDTO());
                } else {
                    userVsAvailableSlots.put(user, new ArrayList<UserAvailabilitySlot>() {{
                        add(entity.toDTO());
                    }});
                }
            }
            Comparator<UserAvailabilitySlot> comparator = new Comparator<UserAvailabilitySlot>() {
                @Override
                public int compare(UserAvailabilitySlot slot1, UserAvailabilitySlot slot2) {
                    OffsetDateTime startTimeSlot1 = slot1.getStartTime();
                    OffsetDateTime startTimeSlot2 = slot2.getStartTime();
                    return startTimeSlot1.compareTo(startTimeSlot2);
                }
            };
            for (Map.Entry<User, List<UserAvailabilitySlot>> userVsAvailableSlot : userVsAvailableSlots.entrySet()) {
                Collections.sort(userVsAvailableSlot.getValue(), comparator);
            }
            tx.commit();
            return Response.ok("calendarIds").build();
        } catch (Exception e) {
            tx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
