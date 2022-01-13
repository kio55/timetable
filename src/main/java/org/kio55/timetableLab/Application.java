package org.kio55.timetableLab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * point of entry
 */
@SpringBootApplication
@ComponentScan(basePackages = "org.kio55.timetableLab")
public class Application {
    /**
     * point of entry
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
