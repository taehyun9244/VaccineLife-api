package com.vaccinelife.vaccinelifeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRequestDto {
    private String contents;
    private String username;
    private Long userId;
    private LocalDateTime createdAt;
}
