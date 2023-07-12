package com.saha.rest.resources;

import com.saha.rest.resources.*;
import com.saha.rest.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.saha.rest.model.User;

import java.util.List;
import com.saha.rest.resources.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2023-07-11T01:03:51.209+05:30[Asia/Kolkata]")
public abstract class UsersApiService {
    public abstract Response createUser(User user,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteUser(Long userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getUser(Long userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getUsers(SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateUser(Long userId,User user,SecurityContext securityContext) throws NotFoundException;
}
