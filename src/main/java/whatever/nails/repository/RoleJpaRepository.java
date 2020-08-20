package whatever.nails.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import whatever.nails.entity.auth.Role;

@Repository
@Cacheable
public interface RoleJpaRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(String roleName);
}
