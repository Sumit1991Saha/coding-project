package com.saha.rest.resources.impl;

import com.saha.model.UserEntity;
import com.saha.persistence.PersistenceManager;
import com.saha.rest.resources.*;
import com.saha.rest.model.*;

import com.saha.rest.model.User;

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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2023-07-11T01:03:51.209+05:30[Asia/Kolkata]")
public class UsersApiServiceImpl extends UsersApiService {

    private EntityManager em;
    private EntityTransaction tx;
    private CrudService<UserEntity> service;
    private String schemaName;

    public UsersApiServiceImpl() {
        em = PersistenceManager.getEntityManager();
        tx = em.getTransaction();
        service = new CrudService<>(UserEntity.class, em);
        schemaName = "openapi";
    }
    @Override
    public Response createUser(User user, SecurityContext securityContext) throws NotFoundException {
        UserEntity userEntity = new UserEntity();
        userEntity.fromDTO(user);
        try {
            tx.begin();
            service.create(userEntity);
            long userId = service.getNextSequenceId(UserEntity.class, schemaName, em);
            if (userId !=0) {
                user.setId(userId);
            }
            tx.commit();
            return Response.ok().entity(user).build();
        } catch (Exception e) {
            tx.rollback();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @Override
    public Response deleteUser(Long userId, SecurityContext securityContext) throws NotFoundException {
        try {
            tx.begin();
            service.removeById(userId);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Deletion successful!")).build();
    }
    @Override
    public Response getUser(Long userId, SecurityContext securityContext) throws NotFoundException {
        UserEntity user = null;
        try {
            tx.begin();
            user = service.findById(userId);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        if (user != null)
            return Response.ok(user).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
    @Override
    public Response getUsers(SecurityContext securityContext) throws NotFoundException {
        List<UserEntity> userEntities = new ArrayList<>();
        try {
            tx.begin();
            userEntities = service.findAll();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        List<User> users = userEntities.stream().map(entity -> entity.toDTO()).collect(Collectors.toList());
        return Response.ok().entity(users).build();
    }
    @Override
    public Response updateUser(Long userId, User user, SecurityContext securityContext) throws NotFoundException {
        UserEntity userEntity = null;
        try {
            tx.begin();
            userEntity = service.findById(userId);
            if (userEntity == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            userEntity.fromDTO(user);
            service.update(userEntity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        if (userEntity != null) {
            User updatedUser = userEntity.toDTO();
            return Response.ok().entity(updatedUser).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
