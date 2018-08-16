package br.com.splessons.persistence.repo;

import br.com.splessons.persistence.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
