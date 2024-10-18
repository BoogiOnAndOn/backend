package com.boogionandon.backend.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkerUpdateEndDateRequestDTO {
    
    private LocalDate startDate;
    private LocalDate endDate;
    
}
