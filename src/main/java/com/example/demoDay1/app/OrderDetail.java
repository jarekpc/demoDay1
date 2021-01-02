package com.example.demoDay1.app;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_details")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book ebook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


}
