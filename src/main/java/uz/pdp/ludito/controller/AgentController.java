package uz.pdp.ludito.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ludito.controller.converter.AgentConverter;
import uz.pdp.ludito.controller.dto.AgentCreateRequest;
import uz.pdp.ludito.controller.dto.AgentCreateResponse;
import uz.pdp.ludito.controller.dto.JwtResponse;
import uz.pdp.ludito.entity.agent.AgentEntity;
import uz.pdp.ludito.service.AgentService;

@RestController
@RequestMapping("/api/v1/agent")
@RequiredArgsConstructor
public class AgentController {
    private final AgentService agentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AgentCreateResponse saveUser(@RequestBody AgentCreateRequest request) {
        AgentEntity agentEntity = agentService.save(AgentConverter.toUserEntity(request));
        return AgentConverter.fromUserEntity(agentEntity);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody AgentCreateRequest request) throws JsonProcessingException {
        return agentService.login(request.getUsername(), request.getPassword());
    }
}
