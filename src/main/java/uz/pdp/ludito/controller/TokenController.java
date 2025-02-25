package uz.pdp.ludito.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.ludito.controller.dto.JwtResponse;
import uz.pdp.ludito.controller.dto.RefreshToken;
import uz.pdp.ludito.service.TokenService;

@RestController
@RequestMapping("/api/v1/agent/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public JwtResponse refreshToken(
            @RequestBody RefreshToken refreshToken
    ) throws JsonProcessingException {
        String accessToken = tokenService.generateAccessToken(refreshToken);
        return new JwtResponse("Bearer " + accessToken);
    }
}
