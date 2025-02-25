package uz.pdp.ludito.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.ludito.controller.dto.JwtResponse;
import uz.pdp.ludito.entity.agent.AgentEntity;
import uz.pdp.ludito.entity.agent.RoleEntity;
import uz.pdp.ludito.entity.enums.UserRole;
import uz.pdp.ludito.exception.RecordNotFoundException;
import uz.pdp.ludito.repository.AgentRepository;
import uz.pdp.ludito.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgentService {
    private final AgentRepository agentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;


    public AgentEntity save(AgentEntity agentEntity) {
        agentEntity.setPassword(passwordEncoder.encode(agentEntity.getPassword()));
        agentEntity.setRoles(List.of(getUserRole()));
        return agentRepository.save(agentEntity);
    }

    public JwtResponse login(String username, String password) throws JsonProcessingException {
        Optional<AgentEntity> optionalUserEntity =
                agentRepository.findByUsername(username);
        if (optionalUserEntity.isEmpty()) {
            throw new RecordNotFoundException(String.format("Agent %s not found", username));
        }
        AgentEntity agentEntity = optionalUserEntity.get();
        boolean matches = passwordEncoder.matches(password, agentEntity.getPassword());
        if (!matches) {
           throw new UsernameNotFoundException(username);
        }

        String accessToken = jwtService.generateAccessToken(agentEntity);
        String refreshToken = jwtService.generateRefreshToken(agentEntity);
        return new JwtResponse("Bearer " + accessToken, refreshToken);
    }

    private RoleEntity getUserRole() {
        return roleRepository.findByRole(UserRole.AGENT);
    }

}
