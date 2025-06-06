package fiap.com.br.gssoa.dto;

import lombok.Data;

@Data
public class CreateEquipamentDTO {
    private String name;
    private Double hourUsedPerDay;
    private Long personId;
}

