package org.quark.controller;



import org.quark.service.ParserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/site")
@Produces(MediaType.APPLICATION_JSON)
public class ParserResource {

    @Inject
    ParserService service;

    @GET
    @Path("/URL")
    public String greeting() {
        service.XMLParser();
        return "OK!";
    }


}