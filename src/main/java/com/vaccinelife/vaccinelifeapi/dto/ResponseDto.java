package com.vaccinelife.vaccinelifeapi.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;


//좋아요 동작/ 유저 아이디,닉네임 중복체크시 이용됨
@Getter
public class ResponseDto {

    private Boolean ok;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int status;

    public ResponseDto(Boolean ok, String msg, int status) {
        this.ok = ok;
        this.msg = msg;
        this.status = status;
    }
}