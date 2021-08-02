package com.vaccinelife.vaccinelifeapi.model;

import com.vaccinelife.vaccinelifeapi.dto.MedicalRequestDto;
import lombok.*;

import javax.persistence.*;
@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
public class Medical extends Timestamped {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    private Long id;

    @Column(nullable = false)
    private String contents;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;



    public Medical(MedicalRequestDto requestDto){
        this.contents = requestDto.getContents();

    }
}
