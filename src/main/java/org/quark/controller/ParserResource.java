package org.quark.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.quark.service.ParserService;
import org.quark.service.RequestService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/url")
@Produces(MediaType.APPLICATION_JSON)
public class ParserResource extends FailResource {

    @Inject
    ParserService parserService;

    @Inject
    RequestService requestService;

    @GET
    @Path("/xml")
    public Response healthCheckForXML() {
        List list = parserService.XMLParser();
        Map mappedUrls = urlsToMapObject(list);

        if (mappedUrls.isEmpty())
            return super.failResponse();

        return super.succesResponse(toJSONObject(mappedUrls));
    }

    @GET
    @Path("/csv")
    public Response healthCheckForCSV() {
        List list = parserService.CSVParser();
        Map mappedUrls = urlsToMapObject(list);

        if (mappedUrls.isEmpty())
            return super.failResponse();
        return super.succesResponse(toJSONObject(mappedUrls));
    }

    private String toJSONObject(Map map) {
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

        return prettyGson.toJson(map);
    }

    private Map urlsToMapObject(List list) {
        return requestService.urlChecker(list);
    }
}