package fiap.com.br.gssoa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiap.com.br.gssoa.dto.CreatePersonDTO;
import fiap.com.br.gssoa.dto.GetPersonDTO;
import fiap.com.br.gssoa.dto.UpdatePersonDTO;
import fiap.com.br.gssoa.model.Person;
import fiap.com.br.gssoa.service.PersonService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pessoa")
@RequiredArgsConstructor
public class PersonController {

  
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody CreatePersonDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPersonDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(personService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetPersonDTO>> listAll() {
        return ResponseEntity.ok(personService.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody UpdatePersonDTO dto) {
        try {
            return ResponseEntity.ok(personService.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            personService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

