package org.quark.controller;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

 class FailResource {

    Response failResponse() {
        throw new WebApplicationException("Parse Exception", 422);
    }

     Response succesResponse(String response) {
         return Response.ok(response).status(200).build();
     }

}