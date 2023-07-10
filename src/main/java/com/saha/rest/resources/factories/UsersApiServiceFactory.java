package com.saha.rest.resources.factories;

import com.saha.rest.resources.UsersApiService;
import com.saha.rest.resources.impl.UsersApiServiceImpl;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2023-07-11T01:03:51.209+05:30[Asia/Kolkata]")
public class UsersApiServiceFactory {
    private final static UsersApiService service = new UsersApiServiceImpl();

    public static UsersApiService getUsersApi() {
        return service;
    }
}
