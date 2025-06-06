package fiap.com.br.gssoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetEquipamentDTO {
    private Long id;
    private String name;
    private Double hourUsedPerDay;
    private Long personId;
}
