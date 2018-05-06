package application.repositories;

import application.models.member.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {
    public Iterable<Member> getAllByMembershipName(String MembershipName);
    public Member getMemberByMembershipName(String MembershipName);
}
