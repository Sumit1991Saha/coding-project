package com.saha.rest.resources.factories;

import com.saha.rest.resources.FetchOverlapApiService;
import com.saha.rest.resources.impl.FetchOverlapApiServiceImpl;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2023-07-13T15:53:38.488+05:30[Asia/Kolkata]")
public class FetchOverlapApiServiceFactory {
    private final static FetchOverlapApiService service = new FetchOverlapApiServiceImpl();

    public static FetchOverlapApiService getFetchOverlapApi() {
        return service;
    }
}
