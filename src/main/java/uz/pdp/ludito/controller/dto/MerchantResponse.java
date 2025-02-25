package uz.pdp.ludito.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MerchantResponse {
    private int id;
    private String name;
}
