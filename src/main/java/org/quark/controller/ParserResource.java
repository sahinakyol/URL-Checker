package org.quark.controller;



import org.quark.service.ParserService;
import org.quark.service.RequestService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;

@Path("/site")
@Produces(MediaType.APPLICATION_JSON)
public class ParserResource {

    @Inject
    ParserService parserService;

    @Inject
    RequestService requestService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/URL")
    public String healthCheck() {
        List list = parserService.XMLParser();
        Map mapped = requestService.urlChecker(list);
        mapped.forEach((k,v)->{
            System.out.println("URL : " + k + " RESPONSE : " + v);
        });
        return "OK!";
    }


}