package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyRequestDto {

    private String type;
    private String gender;
    private String age;
    private int disease;
    private int degree;
    private String aftereffect;
    private Long userId;

}
