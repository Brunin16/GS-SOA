package fiap.com.br.gssoa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fiap.com.br.gssoa.dto.CreatePersonDTO;
import fiap.com.br.gssoa.dto.GetPersonDTO;
import fiap.com.br.gssoa.dto.UpdatePersonDTO;
import fiap.com.br.gssoa.model.Person;
import fiap.com.br.gssoa.model.User;
import fiap.com.br.gssoa.repository.PersonRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

public Person create(CreatePersonDTO dto) {
    User user = new User();
    user.setUsername(dto.getUser().getUsername());
    user.setPassword(dto.getUser().getPassword());

    Person person = Person.builder()
            .fullName(dto.getFullName())
            .email(dto.getEmail())
            .cpf(dto.getCpf())
            .years(dto.getYears())
            .endress(dto.getEndress())
            .country(dto.getCountry())
            .user(user) // associação aqui
            .build();

    return personRepository.save(person);
}


    public Person update(Long id, UpdatePersonDTO dto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        person.setFullName(dto.getFullName());
        person.setEmail(dto.getEmail());
        person.setCpf(dto.getCpf());
        person.setYears(dto.getYears());
        person.setEndress(dto.getEndress());
        person.setCountry(dto.getCountry());

        return personRepository.save(person);
    }

    public GetPersonDTO getById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        return new GetPersonDTO(
                person.getId(),
                person.getFullName(),
                person.getEmail(),
                person.getCpf(),
                person.getYears(),
                person.getEndress(),
                person.getCountry()
        );
    }

    public List<GetPersonDTO> listAll() {
        return personRepository.findAll().stream()
                .map(p -> new GetPersonDTO(
                        p.getId(),
                        p.getFullName(),
                        p.getEmail(),
                        p.getCpf(),
                        p.getYears(),
                        p.getEndress(),
                        p.getCountry()
                ))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        if (!personRepository.existsById(id)) {
            throw new RuntimeException("Pessoa não encontrada");
        }
        personRepository.deleteById(id);
    }
}

