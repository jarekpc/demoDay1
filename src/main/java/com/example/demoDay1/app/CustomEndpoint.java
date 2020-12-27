package com.example.demoDay1.app;

import com.example.demoDay1.service.EbookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@Component
@Endpoint(id="custom")
public class CustomEndpoint {

    @Autowired
    private EbookStoreService eservice;
//
//    public CustomEndpoint(EbookStoreService eservice) {
//        this.eservice = eservice;
//    }

    @ReadOperation
    public ConcurrentMap<String, Integer> getOperation(){
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        for(Book b: eservice.getBooks()){
            map.put("books", map.getOrDefault("books",0)+1);
        }

        return map;
    }

    @WriteOperation
    public String writeOperation(){
        return "write operation";
    }

    @DeleteOperation
    public String deleteOperation(){
        return "delete operation";
    }
}
