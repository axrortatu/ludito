package uz.pdp.ludito.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceResponse {
    private int id;
    private String name;
}
