package uz.pdp.ludito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ludito.entity.agent.RoleEntity;
import uz.pdp.ludito.entity.enums.UserRole;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRole(UserRole role);
}
