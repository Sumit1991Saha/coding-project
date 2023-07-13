package com.saha.rest.resources;

import com.saha.rest.resources.*;
import com.saha.rest.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.util.List;
import com.saha.rest.model.User;
import com.saha.rest.model.UserAvailabilitySlot;

import java.util.List;
import com.saha.rest.resources.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2023-07-13T15:53:38.488+05:30[Asia/Kolkata]")
public abstract class FetchOverlapApiService {
    public abstract Response fetchUserAvailabilityOverlap(List<User> user,SecurityContext securityContext) throws NotFoundException;
}
