package com.mentalhealth.therapistappointment;

import com.mentalhealth.therapistappointment.model.Therapist;
import com.mentalhealth.therapistappointment.repository.TherapistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;
import java.util.Arrays;

@SpringBootApplication
public class TherapistAppointmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(TherapistAppointmentApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(TherapistRepository repository) {
        return args -> {
            repository.save(new Therapist("Dr. Alice", "Psychologist", "Monday - Friday",
                    Arrays.asList(
                        LocalTime.of(10, 0),
                        LocalTime.of(11, 0),
                        LocalTime.of(14, 0),
                        LocalTime.of(15, 0),
                        LocalTime.of(16, 0)
                    )));

            repository.save(new Therapist("Dr. Bob", "Therapist", "Tuesday - Saturday",
                    Arrays.asList(
                        LocalTime.of(9, 0),
                        LocalTime.of(12, 0),
                        LocalTime.of(13, 0),
                        LocalTime.of(15, 0),
                        LocalTime.of(17, 0)
                    )));
        };
    }
}