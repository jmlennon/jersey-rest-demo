package com.kronos.jersey.hello;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

public class HelloTest extends JerseyTest {
    
    @Override
    protected Application configure() {
    	enable(TestProperties.LOG_TRAFFIC);
    	enable(TestProperties.DUMP_ENTITY);
    	return new ResourceConfig(HelloRestService.class);
    }
 
    @Test
    public void testHello() {
    	System.out.println(getBaseUri());
        String hello = target("hello/hello").request().get(String.class);
        assertEquals("Hello World!", hello);
    }
 
    @Test
    public void testGoodbye() {
        GoodBye goodBye = target("hello/goodbye").request().get(GoodBye.class);
        assertEquals("Cruel World!", goodBye.getTarget());
    }
 
    @Test
    public void testAloha() {
        Aloha aloha = target("hello/aloha")
                .queryParam(HelloRestService.QUERY_PARAM_LOCATION, "Honolulu")
                .queryParam(HelloRestService.QUERY_PARAM_SPEAKS_HAWAIIAN, false)
                .request()
                .header(HelloRestService.HEADER_PARAM_TOKEN, "<this-is-a-token>")
                .get(Aloha.class);
        assertEquals("Don Ho!", aloha.getTarget());
    }
}
