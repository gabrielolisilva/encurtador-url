package gabrielsilva.dev.repositories;

import gabrielsilva.dev.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
