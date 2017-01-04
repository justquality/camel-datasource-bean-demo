package com.endava.route;

import com.endava.service.TFileService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Dmitri Cadea on 03.01.17.
 */
@Component
public class TFileRoute extends RouteBuilder {

    @Autowired
    private TFileService tFileService;

    @Override
    public void configure() throws Exception {
        from("file:src/data?noop=true")
                .routeId("save-file-route")
                .log("Saving file.....")
                .bean(tFileService, "save")
                .log("File saved.")
                .to("file:src/data/processed");
    }

}
