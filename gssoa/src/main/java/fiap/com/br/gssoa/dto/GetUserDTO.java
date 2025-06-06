package fiap.com.br.gssoa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetUserDTO {
    private Long id;
    private String username;
}

