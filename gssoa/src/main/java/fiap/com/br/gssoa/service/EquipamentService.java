package fiap.com.br.gssoa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fiap.com.br.gssoa.dto.CreateEquipamentDTO;
import fiap.com.br.gssoa.dto.GetEquipamentDTO;
import fiap.com.br.gssoa.dto.UpdateEquipamentDTO;
import fiap.com.br.gssoa.model.Equipament;
import fiap.com.br.gssoa.model.Person;
import fiap.com.br.gssoa.repository.EquipamentRepository;
import fiap.com.br.gssoa.repository.PersonRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipamentService {

    private final EquipamentRepository equipamentRepository;
    private final PersonRepository personRepository;

    public Equipament create(CreateEquipamentDTO dto) {
        Person person = personRepository.findById(dto.getPersonId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        Equipament equipament = Equipament.builder()
                .name(dto.getName())
                .hourUsedPerDay(dto.getHourUsedPerDay())
                .person(person)
                .build();

        return equipamentRepository.save(equipament);
    }

    public GetEquipamentDTO getById(Long id) {
        Equipament equipament = equipamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        return new GetEquipamentDTO(
                equipament.getId(),
                equipament.getName(),
                equipament.getHourUsedPerDay(),
                equipament.getPerson().getId()
        );
    }

    public List<GetEquipamentDTO> listAll() {
        return equipamentRepository.findAll().stream()
                .map(e -> new GetEquipamentDTO(
                        e.getId(),
                        e.getName(),
                        e.getHourUsedPerDay(),
                        e.getPerson().getId()
                )).collect(Collectors.toList());
    }

    public Equipament update(Long id, UpdateEquipamentDTO dto) {
        Equipament equipament = equipamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        equipament.setName(dto.getName());
        equipament.setHourUsedPerDay(dto.getHourUsedPerDay());

        return equipamentRepository.save(equipament);
    }

    public void delete(Long id) {
        if (!equipamentRepository.existsById(id)) {
            throw new RuntimeException("Equipamento não encontrado");
        }
        equipamentRepository.deleteById(id);
    }
}
