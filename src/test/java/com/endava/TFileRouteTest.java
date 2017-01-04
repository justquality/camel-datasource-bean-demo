package com.endava;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by Dmitri Cadea on 03.01.17.
 */
@RunWith(CamelSpringRunner.class)
@ContextConfiguration(classes = {CamelRouteConfiguration.class})
@DirtiesContext
public class TFileRouteTest extends CamelTestSupport {

    @Produce(uri = "direct:start")
    private ProducerTemplate template;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint result;

    @Before
    public void start() throws Exception {
        result.reset();
    }

    @Test
    public void testSendFile() throws Exception {
        String expectedBody = "<body/>";
        result.expectedBodiesReceived(expectedBody);
        template.sendBodyAndHeader(expectedBody, "CamelFileName", "message.xml");
        result.assertIsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(template.getDefaultEndpoint())
                        .routeId("test-file-route")
                        .log("[Test] Processing file: " + header("CamelFileName"))
                        .to(result);
            }
        };
    }

}
