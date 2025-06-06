package fiap.com.br.gssoa.dto;

import lombok.Data;

@Data
public class CreateEnergyConsumeDTO {
    private Double pricePerHour;
    private Double fixPrice;
    private Integer month;
    private Integer year;
    private Long equipamentId;
}

