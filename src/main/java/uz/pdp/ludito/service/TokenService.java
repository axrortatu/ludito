package uz.pdp.ludito.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.ludito.controller.dto.RefreshToken;
import uz.pdp.ludito.controller.dto.TokenValidationResponse;
import uz.pdp.ludito.entity.agent.AgentEntity;
import uz.pdp.ludito.repository.AgentRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtService jwtService;
    private final AgentRepository agentRepository;

    public String generateAccessToken(RefreshToken refreshTokenDto) throws JsonProcessingException {
        String refreshToken = refreshTokenDto.getRefreshToken();
        jwtService.validateRefreshToken(refreshToken);
        Claims claims = jwtService.refreshTokenClaims(refreshToken);
        String username = claims.getSubject();
        Optional<AgentEntity> optionalUser = agentRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        return jwtService.generateAccessToken(optionalUser.get());
    }
}
