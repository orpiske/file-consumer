package org.apache.camel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.camel.component.file.FileConsumer;
import org.apache.camel.component.file.FileEndpoint;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileOperations;
import org.apache.camel.component.file.GenericFileProcessStrategy;
import org.apache.camel.impl.DefaultCamelContext;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkFile {
    private MyFileEndpoint endpoint;
    private CustomFileConsumer consumer;
    private String inputDir = System.getProperty("input.dir");
    private List<GenericFile<File>> fileList = new ArrayList<>();


    private class CustomFileConsumer extends FileConsumer {
        public CustomFileConsumer(FileEndpoint endpoint, Processor processor, GenericFileOperations<File> operations, GenericFileProcessStrategy<File> processStrategy) {
            super(endpoint, processor, operations, processStrategy);
        }

        @Override
        public boolean pollDirectory(String fileName, List<GenericFile<File>> fileList, int depth) {
            return super.pollDirectory(fileName, fileList, depth);
        }
    }

    private class MyFileEndpoint extends FileEndpoint {
        @Override
        protected FileConsumer newFileConsumer(Processor processor, GenericFileOperations<File> operations) {
            return new CustomFileConsumer(this, processor, operations, createGenericFileStrategy());
        }
    }

    @Setup
    public void prepare() throws Exception {
        CamelContext context = new DefaultCamelContext();
        endpoint = new MyFileEndpoint();
        endpoint.setNoop(true);
        endpoint.setRecursive(true);

        System.out.println("Running with Camel version: " + context.getVersion());

        context.addEndpoint("perf-file", endpoint);
        consumer = (CustomFileConsumer) endpoint.newFileConsumer(exchange -> {}, null);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(15)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testMethod() {
        consumer.pollDirectory(inputDir, fileList, 0);

        System.out.println("Polled file count: " + fileList.size());
    }
}
