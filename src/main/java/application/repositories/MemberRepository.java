package application.repositories;

import application.models.Member;
import application.models.User;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {
    public Iterable<Member> getAllByMembershipName(String MembershipName);
}
