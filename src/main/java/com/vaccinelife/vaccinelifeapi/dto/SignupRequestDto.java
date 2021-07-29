package com.vaccinelife.vaccinelifeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
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


    private Boolean isSurvey;

    public SignupRequestDto(String username, String password, String passwordCheck, String nickname, Boolean isSurvey){}


}