package fiap.com.br.gssoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiap.com.br.gssoa.model.Person;

@Repository
public interface PersonRepository  extends JpaRepository<Person,Long>{

}
