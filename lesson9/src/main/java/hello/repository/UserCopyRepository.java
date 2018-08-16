package hello.repository;

import hello.model.User;
import hello.model.UserCopy;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserCopyRepository extends CrudRepository<UserCopy, Long> {

}

