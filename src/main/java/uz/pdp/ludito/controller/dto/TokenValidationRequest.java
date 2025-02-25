package uz.pdp.ludito.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TokenValidationRequest {
    private String token;
}
