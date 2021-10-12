package org.apache.camel;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        // from("file:{{input.dir}}?noop=true&fileName=camel-full-build-395.log")
        //     .split(body().tokenize("\n")).streaming()
        //     .log("${body}")
//            from("file:{{input.dir}}?noop=true&resumeStrategy=#testFileResume")
        from("file:{{input.dir}}?noop=true&recursive=true")
//                .log("Moving ${header[CamelFileName]}")
                .to("file:{{output.dir}}");
    }

}
