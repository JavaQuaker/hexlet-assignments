package exercise.repository;

import exercise.model.User;
//import org.apache.el.stream.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // BEGIN
    Optional<User> findByEmail(String email);
    // END
}
