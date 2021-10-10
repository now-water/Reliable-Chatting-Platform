package com.rabbitmq.mqserver.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FCMInitializer {

    private static final Logger logger = LoggerFactory.getLogger(FCMInitializer.class);

    @Value("${fcm.tempFilePath}")
    private String FIREBASE_CONFIG_PATH;

    @PostConstruct
    public void initialize() {
        try {
            InputStream fcmOptionsInputStream = FIREBASE_CONFIG_PATH.startsWith("/") ? new FileSystemResource(FIREBASE_CONFIG_PATH).getInputStream()
                    : new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream();

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(fcmOptionsInputStream)).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
