package fiap.com.br.gssoa.dto;

import lombok.Data;

@Data
public class CreatePersonDTO {
    private String fullName;
    private String email;
    private String cpf;
    private Integer years;
    private String endress;
    private String country;
    private CreateUserDTO user;
}
