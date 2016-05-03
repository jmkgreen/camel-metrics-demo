package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * Created by James on 01/05/2016.
 */
@Configuration
public class StartRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:start?fixedRate=true&period=1000")
                .setBody(constant("Hello, world"))
                .to("metrics:counter:start")
                .to("direct:slow")
                .to("log:foo");

        from("direct:slow")
                .delayer(1000)
                .to("log:foo");
    }
}
