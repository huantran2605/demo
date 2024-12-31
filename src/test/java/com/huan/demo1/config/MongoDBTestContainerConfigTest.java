package com.huan.demo1.config;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class MongoDBTestContainerConfigTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer =
            new MongoDBContainer(DockerImageName.parse("mongo:4.4.2"));

    @BeforeAll
    public static void startContainer() {
        System.out.println("MongoDB container started.");
        mongoDBContainer.start();
    }

    @AfterAll
    public static void stopContainer() {
        System.out.println("MongoDB container stopped.");
        mongoDBContainer.stop();
    }

    @Test
    void testMongoDbContainerIsRunning() {
        assertThat(mongoDBContainer.isRunning()).isTrue();
    }
}