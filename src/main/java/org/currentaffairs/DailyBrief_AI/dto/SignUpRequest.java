package org.currentaffairs.DailyBrief_AI.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {
    private Long id;
    private String name;
    private String email;
    private String phoneNo;
    private String password;
}
