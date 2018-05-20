package application.repository;

import application.models.MemberRole;
import application.models.SecurityRole;
import org.springframework.data.repository.CrudRepository;

public interface MemberRoleRepository extends CrudRepository<MemberRole, Integer> {
    MemberRole findByRoleCode(String roleCode);
}
