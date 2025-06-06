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

import fiap.com.br.gssoa.dto.CreateEquipamentDTO;
import fiap.com.br.gssoa.dto.GetEquipamentDTO;
import fiap.com.br.gssoa.dto.UpdateEquipamentDTO;
import fiap.com.br.gssoa.model.Equipament;
import fiap.com.br.gssoa.service.EquipamentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/equipamento")
@RequiredArgsConstructor
public class EquipamentController {

    
    private final EquipamentService equipamentService;

    @PostMapping
    public ResponseEntity<Equipament> create(@RequestBody CreateEquipamentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipamentService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEquipamentDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(equipamentService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GetEquipamentDTO>> listAll() {
        return ResponseEntity.ok(equipamentService.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipament> update(@PathVariable Long id, @RequestBody UpdateEquipamentDTO dto) {
        try {
            return ResponseEntity.ok(equipamentService.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            equipamentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

