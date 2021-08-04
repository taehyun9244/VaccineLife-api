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

    @ManyToOne(fetch = FetchType.EAGER)
    private VacBoard vacBoard;

    public Ip(String ip,VacBoard vacBoard){
        this.ip = ip;
        this.vacBoard = vacBoard;
    }

}