package fiap.com.br.gssoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetEnergyConsumeDTO {
    private Long id;
    private Double pricePerHour;
    private Double fixPrice;
    private Integer month;
    private Integer year;
    private Long equipamentId;
}
