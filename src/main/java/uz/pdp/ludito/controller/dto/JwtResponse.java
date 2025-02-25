package uz.pdp.ludito.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponse {
    private String accessToken;
    private String refreshToken;

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
