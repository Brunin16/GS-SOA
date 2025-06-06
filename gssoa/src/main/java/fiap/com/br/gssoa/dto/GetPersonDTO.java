package fiap.com.br.gssoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPersonDTO {
    private Long id;
    private String fullName;
    private String email;
    private String cpf;
    private Integer years;
    private String endress;
    private String country;
}

