package br.com.splessons.persistence.repo;

import br.com.splessons.persistence.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
