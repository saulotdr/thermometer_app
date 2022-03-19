package com.saulotdr.apps.thermometer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new AppListener());
        app.run(args);
    }

    public static class AppListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
        @Override
        public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
            System.setProperty("aws.accessKeyId", "AKIA5M3FRD66AHX4URGX");
            System.setProperty("aws.secretAccessKey", "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");
        }
    }
}