package com.vaccinelife.vaccinelifeapi.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.vaccinelife.vaccinelifeapi.model.Survey;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VBoard;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.OneToOne;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyRequestDto {

    private String type;
    private String gender;
    private int age;
    private Boolean disease;
    private int degree;
    private String aftereffect;
    private Long userId;


}
