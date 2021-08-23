package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class VacPrevNextDto {
    private VacBoard nextId;
    private VacBoard prevId;

}
