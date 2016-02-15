package com.kronos.jersey.hello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        String expectedGreeting = "Aloha!";
        String expectedLocation = "Honolulu";
        String expectedToken = "<expected-header-token>";
        Aloha aloha = target("hello/aloha")
                .queryParam(HelloRestService.QUERY_PARAM_LOCATION, expectedLocation)
                .queryParam(HelloRestService.QUERY_PARAM_SPEAKS_HAWAIIAN, true)
                .request()
                .header(HelloRestService.HEADER_PARAM_TOKEN, expectedToken)
                .get(Aloha.class);
        assertTrue(aloha.getTarget().contains(expectedGreeting));
        assertTrue(aloha.getTarget().contains(expectedLocation));
        assertTrue(aloha.getTarget().contains(expectedToken));
    }
}
