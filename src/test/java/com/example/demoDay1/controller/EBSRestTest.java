package com.example.demoDay1.controller;

import com.example.demoDay1.app.Book;
import com.example.demoDay1.service.EbookStoreService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EBSRestTest {

    @LocalServerPort
    private int port;

    private EbookStoreService ebsservice;

    private MockMvc mockMvc;

    private final List<Book> books = new ArrayList<>();

    @Test
    public void testresttemplate() {
        TestRestTemplate template = new TestRestTemplate();
        template.withBasicAuth("admin", "password12")
                .getForObject("http://localhost:" + port, String.class)
                .contains("EbookStore");
    }

    private String examplePostRes = "{\"title\":\"Lotta Life\",\r\n" +
            "	\"description\":\"young girl tells about her life\",\r\n" +
            "	\"price\":0.0,\r\n" +
            "	\"publishDate\":\"2005-01-01\",\r\n" +
            "	\"path\":\"\",\r\n" +
            "	\"id\":1}";

    //    @Before
    public void setup() {
        byte[] file = new byte[255];
        books.add(new Book("test", "test", 0.0, Date.valueOf("2001-12-12"), "", file));
        when(ebsservice.getBooks()).thenReturn(books);
    }

//    public void testGetService() throws Exception
//    {
//        mockMvc.perform(get("/rest/books").with(httpBasic("psimonis","1234")))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)));
//    }
}
