package com.saha.rest.resources;

import com.saha.rest.model.*;
import com.saha.rest.resources.FetchOverlapApiService;
import com.saha.rest.resources.factories.FetchOverlapApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import com.saha.rest.model.AvailabilitySlot;
import java.util.List;
import com.saha.rest.model.User;

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

@Path("/fetch-overlap")


@io.swagger.annotations.Api(description = "the fetch-overlap API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2023-07-13T15:53:38.488+05:30[Asia/Kolkata]")
public class FetchOverlapApi  {
   private final FetchOverlapApiService delegate;

   public FetchOverlapApi(@Context ServletConfig servletContext) {
      FetchOverlapApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("FetchOverlapApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (FetchOverlapApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = FetchOverlapApiServiceFactory.getFetchOverlapApi();
      }

      this.delegate = delegate;
   }

    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "fetch user availability overlap", notes = "fetch user availability overlap.", response = AvailabilitySlot.class, responseContainer = "List", tags={ "user-availability-overlap", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Fetched Successfully", response = AvailabilitySlot.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Conflict. user with given details already exists.", response = Void.class) })
    public Response fetchUserAvailabilityOverlap(@ApiParam(value = "" ) @Valid List<User> user
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.fetchUserAvailabilityOverlap(user, securityContext);
    }
}
