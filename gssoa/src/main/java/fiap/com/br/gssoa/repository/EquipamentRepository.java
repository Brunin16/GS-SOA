package fiap.com.br.gssoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiap.com.br.gssoa.model.Equipament;

@Repository
public interface EquipamentRepository extends JpaRepository<Equipament, Long> {

}
