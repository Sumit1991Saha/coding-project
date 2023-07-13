package com.saha.rest.resources.impl;

import com.saha.model.UserAvailableSlotEntity;
import com.saha.model.UserEntity;
import com.saha.persistence.PersistenceManager;
import com.saha.rest.resources.*;
import com.saha.rest.model.*;

import com.saha.rest.model.User;
import com.saha.rest.model.UserAvailabilitySlot;

import java.util.ArrayList;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2023-07-12T20:23:11.968+05:30[Asia/Kolkata]")
public class UsersApiServiceImpl extends UsersApiService {
    private EntityManager em;
    private EntityTransaction tx;
    private CrudService<UserEntity> userCrudService;
    private CrudService<UserAvailableSlotEntity> availableSlotCrudService;
    private String schemaName;

    public UsersApiServiceImpl() {
        em = PersistenceManager.getEntityManager();
        tx = em.getTransaction();
        userCrudService = new CrudService<>(UserEntity.class, em);
        availableSlotCrudService = new CrudService<>(UserAvailableSlotEntity.class, em);
        schemaName = "openapi";
    }
    @Override
    public Response createUser(User user, SecurityContext securityContext) throws NotFoundException {
        UserEntity userEntity = new UserEntity();
        userEntity.fromDTO(user);
        try {
            tx.begin();
            userCrudService.create(userEntity);
            long userId = userCrudService.getNextSequenceId(UserEntity.class, schemaName, em);
            if (userId !=0) {
                user.setId(userId);
            }
            tx.commit();
            return Response.ok().entity(user).build();
        } catch (Exception e) {
            tx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Override
    public Response deleteUser(Long userId, SecurityContext securityContext) throws NotFoundException {
        try {
            tx.begin();
            userCrudService.removeById(userId);
            tx.commit();
            return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Deletion successful!")).build();
        } catch (Exception e) {
            tx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Override
    public Response getUser(Long userId, SecurityContext securityContext) throws NotFoundException {
        UserEntity user = null;
        try {
            tx.begin();
            user = userCrudService.findById(userId);
            tx.commit();
            if (user != null)
                return Response.ok(user).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            tx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Override
    public Response getUsers(SecurityContext securityContext) throws NotFoundException {
        List<UserEntity> userEntities = new ArrayList<>();
        try {
            tx.begin();
            userEntities = userCrudService.findAll();
            tx.commit();
            List<User> users = userEntities.stream().map(entity -> entity.toDTO()).collect(Collectors.toList());
            return Response.ok().entity(users).build();
        } catch (Exception e) {
            tx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Override
    public Response updateUser(Long userId, User user, SecurityContext securityContext) throws NotFoundException {
        UserEntity userEntity = null;
        try {
            tx.begin();
            userEntity = userCrudService.findById(userId);
            if (userEntity == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            userEntity.fromDTO(user);
            userCrudService.update(userEntity);
            tx.commit();
            if (userEntity != null) {
                User updatedUser = userEntity.toDTO();
                return Response.ok().entity(updatedUser).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            tx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Override
    public Response createUserAvailabilitySlot(Long userId, UserAvailabilitySlot userAvailabilitySlot, SecurityContext securityContext) throws NotFoundException {
        UserAvailableSlotEntity availableSlotEntity = new UserAvailableSlotEntity();
        try {
            tx.begin();
            UserEntity userEntity = userCrudService.findById(userId);
            tx.commit();
            //Adding a check so that availability slot can only be set for the corresponding user's calendar only not in any other's calendar
            if (userEntity.getCalendarEntity().getId() != userAvailabilitySlot.getCalendarId()) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            availableSlotEntity.fromDTO(userAvailabilitySlot, userEntity);
            tx.begin();
            availableSlotCrudService.create(availableSlotEntity);
            tx.commit();
            return Response.ok().entity(availableSlotEntity).build();
        } catch (Exception e) {
            tx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public Response getUserAvailabilitySlots(Long userId, SecurityContext securityContext) throws NotFoundException {
        try {
            tx.begin();
            UserEntity userEntity = userCrudService.findById(userId);
            long calendarId = userEntity.getCalendarEntity().getId();
            List<UserAvailableSlotEntity> userAvailableSlotEntities = availableSlotCrudService.findByCalendarId(calendarId);
            tx.commit();
            List<UserAvailabilitySlot> userAvailabilitySlots = userAvailableSlotEntities.stream()
                    .map(entity -> entity.toDTO())
                    .collect(Collectors.toList());
            return Response.ok().entity(userAvailabilitySlots).build();
        } catch (Exception e) {
            tx.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
