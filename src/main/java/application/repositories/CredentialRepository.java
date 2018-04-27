package application.repositories;

import application.models.Credential;
import application.models.Member;
import org.springframework.data.repository.CrudRepository;

public interface CredentialRepository extends CrudRepository<Credential,Long> {

}
