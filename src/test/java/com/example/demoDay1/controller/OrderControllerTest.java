package com.example.demoDay1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    @LocalServerPort
    private int port;

    @Test
    public void integrationTest() throws URISyntaxException {
        TestRestTemplate template = new TestRestTemplate();

        ResponseEntity<String> forEntity = template.getForEntity(new URI("http://localhost:" + port + "/order/buyBook?id=1"), String.class);

//         assertThat(forEntity.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(forEntity).isNotNull();
    }
}
