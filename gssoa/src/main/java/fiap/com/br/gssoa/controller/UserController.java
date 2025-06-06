package fiap.com.br.gssoa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiap.com.br.gssoa.dto.CreateUserDTO;
import fiap.com.br.gssoa.dto.GetUserDTO;
import fiap.com.br.gssoa.model.User;
import fiap.com.br.gssoa.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UserController {

  
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody CreateUserDTO dto) {
        User user = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<GetUserDTO>> listAll() {
        return ResponseEntity.ok(userService.listAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

