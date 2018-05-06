package application.repositories;

import application.models.Credential;
import application.models.member.Member;
import org.springframework.data.repository.CrudRepository;

public interface CredentialRepository extends CrudRepository<Credential,Long> {

    public Credential findCredentialByMember(Member member);
}
