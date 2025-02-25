package uz.pdp.ludito.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import uz.pdp.ludito.entity.enums.UserRole;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class AgentCreateResponse {
    private int id;
    private String username;
    private List<UserRole> roles;
}
