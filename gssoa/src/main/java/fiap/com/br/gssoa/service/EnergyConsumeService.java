package fiap.com.br.gssoa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fiap.com.br.gssoa.dto.CreateEnergyConsumeDTO;
import fiap.com.br.gssoa.dto.GetEnergyConsumeDTO;
import fiap.com.br.gssoa.dto.UpdateEnergyConsumeDTO;
import fiap.com.br.gssoa.model.EnergyConsume;
import fiap.com.br.gssoa.model.Equipament;
import fiap.com.br.gssoa.repository.EnergyConsumeRepository;
import fiap.com.br.gssoa.repository.EquipamentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnergyConsumeService {

    private final EnergyConsumeRepository energyConsumeRepository;
    private final EquipamentRepository equipamentRepository;

    public EnergyConsume create(CreateEnergyConsumeDTO dto) {
        Equipament equipament = equipamentRepository.findById(dto.getEquipamentId())
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        EnergyConsume consumo = EnergyConsume.builder()
                .pricePerHour(dto.getPricePerHour())
                .fixPrice(dto.getFixPrice())
                .month(dto.getMonth())
                .year(dto.getYear())
                .equipament(equipament)
                .build();

        return energyConsumeRepository.save(consumo);
    }

    public EnergyConsume update(Long id, UpdateEnergyConsumeDTO dto) {
        EnergyConsume consumo = energyConsumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumo não encontrado"));

        consumo.setPricePerHour(dto.getPricePerHour());
        consumo.setFixPrice(dto.getFixPrice());

        return energyConsumeRepository.save(consumo);
    }

    public GetEnergyConsumeDTO getById(Long id) {
        EnergyConsume ec = energyConsumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumo não encontrado"));

        return new GetEnergyConsumeDTO(
                ec.getId(),
                ec.getPricePerHour(),
                ec.getFixPrice(),
                ec.getMonth(),
                ec.getYear(),
                ec.getEquipament().getId()
        );
    }

    public List<GetEnergyConsumeDTO> listAll() {
        return energyConsumeRepository.findAll().stream()
                .map(ec -> new GetEnergyConsumeDTO(
                        ec.getId(),
                        ec.getPricePerHour(),
                        ec.getFixPrice(),
                        ec.getMonth(),
                        ec.getYear(),
                        ec.getEquipament().getId()
                ))
                .collect(Collectors.toList());
    }

    public List<GetEnergyConsumeDTO> listByEquipament(Long equipamentId) {
        Equipament equipament = equipamentRepository.findById(equipamentId)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        return equipament.getEnergyConsumes().stream()
                .map(ec -> new GetEnergyConsumeDTO(
                        ec.getId(),
                        ec.getPricePerHour(),
                        ec.getFixPrice(),
                        ec.getMonth(),
                        ec.getYear(),
                        equipament.getId()
                ))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        if (!energyConsumeRepository.existsById(id)) {
            throw new RuntimeException("Consumo não encontrado");
        }
        energyConsumeRepository.deleteById(id);
    }

    public Double calculateMonthlyTotal(Long personId, Integer month, Integer year) {
        return equipamentRepository.findAll().stream()
                .filter(eq -> eq.getPerson().getId().equals(personId))
                .flatMap(eq -> eq.getEnergyConsumes().stream())
                .filter(ec -> ec.getMonth().equals(month) && ec.getYear().equals(year))
                .mapToDouble(ec -> (ec.getPricePerHour() * ec.getEquipament().getHourUsedPerDay() * 30)
                        + ec.getFixPrice())
                .sum();
    }
}

