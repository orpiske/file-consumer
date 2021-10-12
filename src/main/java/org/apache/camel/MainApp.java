package org.apache.camel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.camel.main.Main;
import org.apache.camel.util.StopWatch;

/**
 * A Camel Application
 */
public class MainApp {
    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        String baseOutputDir = System.getProperty("base.output.dir", "target");

        File baseOutput = new File(baseOutputDir);
        baseOutput.mkdirs();

        Path tmpOutputDir = Files.createTempDirectory(baseOutput.toPath(), "test");
        System.out.println("Copying files to: " + tmpOutputDir.toString());
        System.setProperty("output.dir", tmpOutputDir.toString());

        StopWatch ws = new StopWatch();
        Main main = new Main();
        main.configure().addRoutesBuilder(new MyRouteBuilder());
        main.run(args);

        System.out.println("Test run in " + ws.taken() + " milliseconds");
    }
}

