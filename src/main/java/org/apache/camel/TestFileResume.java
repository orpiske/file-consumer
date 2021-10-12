//package org.apache.camel;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.apache.camel.component.file.consumer.FileConsumerResumeStrategy;
//import org.apache.camel.component.file.consumer.FileResumeInfo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class TestFileResume implements FileConsumerResumeStrategy {
//    private static final Logger LOG = LoggerFactory.getLogger(TestFileResume.class);
//
//    @Override
//    public void resume(FileResumeInfo resumeInfo) {
//        int current = 0;
//        int count = 0;
//
//        File[] input = resumeInfo.getInputFiles();
//        File[] tmp = Arrays.copyOf(input, input.length);
//        for (File file : resumeInfo.getInputFiles()) {
//            current++;
//
//            if (current % 2 == 0) {
//                LOG.info("Including file {}", file);
//                count++;
//                tmp[count] = file;
//            } else {
//                LOG.info("Skipping file {}", file);
//            }
//        }
//
//        resumeInfo.setOutputFiles(Arrays.copyOf(tmp, count));
//    }
//}
