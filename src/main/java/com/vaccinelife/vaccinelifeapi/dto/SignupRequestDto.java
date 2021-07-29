package com.vaccinelife.vaccinelifeapi.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class SignupRequestDto {

    @NotBlank(message = "ID는 필수 입력 값입니다.")
    private String username;

    @NotBlank(message = "PW는 필수 입력 값입니다.")
    private String password;
    private String passwordChecker;
    private boolean admin = false;
    private String adminToken = "";

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    private Boolean isVaccine;

    private String type;

    private int degree;

    private String gender;

    private int age;

    private Boolean disease;

    private String afterEffect;


    public SignupRequestDto(String username, String password, String passwordCheck, String nickname,Boolean isVaccine, String type, int degree, String gender, int age, Boolean disease, String afterEffect){


    }

    public SignupRequestDto(SignupRequestDto signupRequestDto) {
        this.nickname=signupRequestDto.getNickname();
        this.isVaccine=signupRequestDto.getIsVaccine();
        this.type= signupRequestDto.getType();
        this.degree=signupRequestDto.getDegree();
        this.gender=signupRequestDto.getGender();
        this.age=signupRequestDto.getAge();
        this.disease=signupRequestDto.getDisease();
        this.afterEffect=signupRequestDto.getAfterEffect();
    }


}