package uz.pdp.ludito.controller.converter;


import uz.pdp.ludito.controller.dto.AgentCreateRequest;
import uz.pdp.ludito.controller.dto.AgentCreateResponse;
import uz.pdp.ludito.controller.dto.AgentGetResponse;
import uz.pdp.ludito.entity.agent.RoleEntity;
import uz.pdp.ludito.entity.agent.AgentEntity;

public class AgentConverter {

    public static AgentCreateResponse fromUserEntity(AgentEntity agentEntity) {
        return AgentCreateResponse.builder()
                .id(agentEntity.getId())
                .username(agentEntity.getUsername())
                .roles(agentEntity.getRoles().stream().map(RoleEntity::getRole).toList())
                .build();
    }

    public static AgentEntity toUserEntity(AgentCreateRequest request) {
        return AgentEntity.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }

    public static AgentGetResponse toUserGetResponseDto(AgentEntity agentEntity) {
        return AgentGetResponse.builder()
                .userId(agentEntity.getId())
                .email(agentEntity.getEmail())
                .name(agentEntity.getName())
                .phone(agentEntity.getUsername())
                .build();
    }
}
