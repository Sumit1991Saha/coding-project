package com.saha.rest.resources;

import com.saha.rest.resources.*;
import com.saha.rest.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.saha.rest.model.User;
import com.saha.rest.model.UserAvailabilitySlot;

import java.util.List;
import com.saha.rest.resources.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2023-07-12T20:23:11.968+05:30[Asia/Kolkata]")
public abstract class UsersApiService {
    public abstract Response createUser(User user,SecurityContext securityContext) throws NotFoundException;
    public abstract Response createUserAvailabilitySlot(Long userId,UserAvailabilitySlot userAvailabilitySlot,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteUser(Long userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getUser(Long userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getUserAvailabilitySlots(Long userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getUsers(SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateUser(Long userId,User user,SecurityContext securityContext) throws NotFoundException;
}
