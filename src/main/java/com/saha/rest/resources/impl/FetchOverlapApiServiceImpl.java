package com.saha.rest.resources.impl;

import com.saha.rest.resources.*;
import com.saha.rest.model.*;

import java.util.List;
import com.saha.rest.model.User;
import com.saha.rest.model.UserAvailabilitySlot;

import java.util.List;
import com.saha.rest.resources.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2023-07-13T15:53:38.488+05:30[Asia/Kolkata]")
public class FetchOverlapApiServiceImpl extends FetchOverlapApiService {
    @Override
    public Response fetchUserAvailabilityOverlap(List<User> user, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
