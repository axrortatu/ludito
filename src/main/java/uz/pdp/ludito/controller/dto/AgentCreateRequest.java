package uz.pdp.ludito.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgentCreateRequest {
    private String username;
    private String password;
}
