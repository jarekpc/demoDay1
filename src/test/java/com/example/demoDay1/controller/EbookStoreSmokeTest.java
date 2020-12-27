package com.example.demoDay1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EbookStoreSmokeTest {

    @Autowired
    EbookStoreController ebscontroller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(ebscontroller).isNotNull();
    }
}
