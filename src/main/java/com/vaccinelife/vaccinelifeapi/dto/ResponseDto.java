package com.vaccinelife.vaccinelifeapi.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;


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