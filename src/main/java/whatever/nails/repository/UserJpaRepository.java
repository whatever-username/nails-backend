package whatever.nails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatever.nails.entity.auth.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
