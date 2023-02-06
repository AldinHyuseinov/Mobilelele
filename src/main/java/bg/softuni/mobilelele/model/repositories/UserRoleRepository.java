package bg.softuni.mobilelele.model.repositories;

import bg.softuni.mobilelele.model.entities.UserRole;
import bg.softuni.mobilelele.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRole(Role role);
}
