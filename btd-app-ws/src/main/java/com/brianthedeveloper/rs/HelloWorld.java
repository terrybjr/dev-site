package com.brianthedeveloper.rs;

import com.brianthedeveloper.helloworld.HelloWorldSLS;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/example")
public class HelloWorld {
    @EJB
    HelloWorldSLS helloWorldSLS;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Successful, returning hello world"
                    )
            }
    )
    public String getHello() {
        return helloWorldSLS.sayHello();
    }

}
