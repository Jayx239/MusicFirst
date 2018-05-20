package application.repository;

import application.models.SecurityRole;
import org.springframework.data.repository.CrudRepository;

public interface SecurityRoleRepository extends CrudRepository<SecurityRole, Integer> {
    SecurityRole findByRoleCode(String roleCode);
}
