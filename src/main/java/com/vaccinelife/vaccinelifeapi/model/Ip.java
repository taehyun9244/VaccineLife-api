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
    private Long id;

    @Column(nullable = false)
    private String ip;

    @ManyToOne(fetch = FetchType.LAZY)
    private VacBoard vacBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    private QuarBoard quarBoard;

    public Ip(String ip,VacBoard vacBoard){
        this.ip = ip;
        this.vacBoard = vacBoard;
    }
    public Ip(String ip, QuarBoard quarBoard){
        this.ip = ip;
        this.quarBoard = quarBoard;
    }

}