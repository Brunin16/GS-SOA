package fiap.com.br.gssoa.service;

import fiap.com.br.gssoa.dto.CreateUserDTO;
import fiap.com.br.gssoa.dto.GetUserDTO;
import fiap.com.br.gssoa.model.User;
import fiap.com.br.gssoa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(CreateUserDTO dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword()) 
                .build();

        return userRepository.save(user);
    }

    public GetUserDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new GetUserDTO(
                user.getId(),
                user.getUsername()
        );
    }

    public List<fiap.com.br.gssoa.dto.GetUserDTO> listAll() {
        return userRepository.findAll().stream()
                .map(user -> new GetUserDTO(
                        user.getId(),
                        user.getUsername()
                ))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }
}
