package com.kronos.jersey.hello;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloRestService {

    public static final String HEADER_PARAM_TOKEN = "token";
    public static final String QUERY_PARAM_LOCATION = "location";
    public static final String QUERY_PARAM_SPEAKS_HAWAIIAN = "speaksHawaiian";

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return "Hello World!";
    }

    @GET
    @Path("goodbye")
    @Produces(MediaType.APPLICATION_JSON)
    public GoodBye getGoodbye() {
        return new GoodBye("Cruel World!");
    }

    @GET
    @Path("aloha")
    @Produces(MediaType.APPLICATION_JSON)
    public Aloha getAloha(
            @HeaderParam(HEADER_PARAM_TOKEN) String token, 
            @QueryParam(QUERY_PARAM_LOCATION) String location, 
            @QueryParam(QUERY_PARAM_SPEAKS_HAWAIIAN) @DefaultValue("true") Boolean speaksHawaiian)
    {
        String greeting = (speaksHawaiian ? "Aloha!" : "Hey Dude!");
        if (location != null) {
            greeting = greeting + " @" + location;
        }
        if (token != null) {
            greeting = greeting + "/" + token;
        }
        return new Aloha(greeting);
    }
}
