package uz.pdp.ludito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.ludito.entity.agent.AgentEntity;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<AgentEntity, Integer> {
    Optional<AgentEntity> findByUsername(String username);
}
