package com.example.demoDay1.app;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", length = 50, nullable = false)
    private long id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "order_date", nullable = false)
    private Date orderdate;

    @Column(name = "order_num", nullable = false)
    private long ordernum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person customer;


}
