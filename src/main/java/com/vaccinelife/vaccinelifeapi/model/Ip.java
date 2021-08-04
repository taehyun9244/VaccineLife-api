package com.vaccinelife.vaccinelifeapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Ip {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long totalVisitors;

    @Column(nullable = false)
    private String Ip;

    public Ip(String hits){
        this.Ip += hits;
    }
}