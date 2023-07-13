package com.saha.rest.resources;

import com.saha.rest.model.*;
import com.saha.rest.resources.UsersApiService;
import com.saha.rest.resources.factories.UsersApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import com.saha.rest.model.User;
import com.saha.rest.model.UserAvailabilitySlot;

import java.util.Map;
import java.util.List;
import com.saha.rest.resources.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/users")


@io.swagger.annotations.Api(description = "the users API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2023-07-12T20:23:11.968+05:30[Asia/Kolkata]")
public class UsersApi  {
   private final UsersApiService delegate;

   public UsersApi(@Context ServletConfig servletContext) {
      UsersApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("UsersApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (UsersApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = UsersApiServiceFactory.getUsersApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create a new user", notes = "Creates a new user for given payload and returns the id.", response = User.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Created successfully", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict. user with given details already exists.", response = Void.class) })
    public Response createUser(@ApiParam(value = "" ) @Valid User user
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createUser(user, securityContext);
    }
    @POST
    @Path("/{user-id}/availability")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create a new availability for a given user", notes = "Creates a new availability for a given user and returns the id.", response = UserAvailabilitySlot.class, tags={ "user-availability", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Created successfully", response = UserAvailabilitySlot.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict.", response = Void.class) })
    public Response createUserAvailabilitySlot(@ApiParam(value = "ID for user id which needs to be operated on.",required=true) @PathParam("user-id") Long userId
,@ApiParam(value = "" ) @Valid UserAvailabilitySlot userAvailabilitySlot
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createUserAvailabilitySlot(userId, userAvailabilitySlot, securityContext);
    }
    @DELETE
    @Path("/{user-id}")
    
    
    @io.swagger.annotations.ApiOperation(value = "Delete existing user using user id.", notes = "Delete existing user using user id.", response = Void.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Deleted Successfully", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User with given id Not Found", response = Void.class) })
    public Response deleteUser(@ApiParam(value = "ID for user id which needs to be operated on.",required=true) @PathParam("user-id") Long userId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteUser(userId, securityContext);
    }
    @GET
    @Path("/{user-id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get a JSON representation of user for the given user id.", notes = "Get a JSON representation of user for the given user id.", response = User.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Fetched Successfully", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "user with given id Not Found", response = Void.class) })
    public Response getUser(@ApiParam(value = "ID for user id which needs to be operated on.",required=true) @PathParam("user-id") Long userId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getUser(userId, securityContext);
    }
    @GET
    @Path("/{user-id}/availability")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Returns a list of the given user's availability.", notes = "Returns list of all the availability slots for a given user. <br> ", response = UserAvailabilitySlot.class, responseContainer = "List", tags={ "user-availability", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Fetched successfully", response = UserAvailabilitySlot.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found", response = Void.class) })
    public Response getUserAvailabilitySlots(@ApiParam(value = "ID for user id which needs to be operated on.",required=true) @PathParam("user-id") Long userId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getUserAvailabilitySlots(userId, securityContext);
    }
    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Returns a list of all the user's details.", notes = "Returns list of all the users. <br> ", response = User.class, responseContainer = "List", tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Fetched successfully", response = User.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not Found", response = Void.class) })
    public Response getUsers(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getUsers(securityContext);
    }
    @PUT
    @Path("/{user-id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update existing user using JSON representation.", notes = "Update existing user using JSON representation.", response = User.class, tags={ "user", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Updated Successfully", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "user with given id Not Found", response = Void.class) })
    public Response updateUser(@ApiParam(value = "ID for user id which needs to be operated on.",required=true) @PathParam("user-id") Long userId
,@ApiParam(value = "" ) @Valid User user
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateUser(userId, user, securityContext);
    }
}
