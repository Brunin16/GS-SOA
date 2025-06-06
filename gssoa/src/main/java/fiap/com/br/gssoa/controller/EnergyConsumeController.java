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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fiap.com.br.gssoa.dto.CreateEnergyConsumeDTO;
import fiap.com.br.gssoa.dto.GetEnergyConsumeDTO;
import fiap.com.br.gssoa.dto.UpdateEnergyConsumeDTO;
import fiap.com.br.gssoa.model.EnergyConsume;
import fiap.com.br.gssoa.service.EnergyConsumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/consumos")
@RequiredArgsConstructor
public class EnergyConsumeController {

  private final EnergyConsumeService energyConsumeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateEnergyConsumeDTO dto) {
        try {
            EnergyConsume created = energyConsumeService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid UpdateEnergyConsumeDTO dto) {
        try {
            EnergyConsume updated = energyConsumeService.update(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(energyConsumeService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<GetEnergyConsumeDTO>> listAll() {
        return ResponseEntity.ok(energyConsumeService.listAll());
    }

    @GetMapping("/by-equipament/{equipamentId}")
    public ResponseEntity<?> listByEquipament(@PathVariable Long equipamentId) {
        try {
            return ResponseEntity.ok(energyConsumeService.listByEquipament(equipamentId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/total")
    public ResponseEntity<?> calculateMonthlyTotal(
            @RequestParam Long personId,
            @RequestParam Integer month,
            @RequestParam Integer year) {
        try {
            Double total = energyConsumeService.calculateMonthlyTotal(personId, month, year);
            return ResponseEntity.ok(total);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            energyConsumeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

