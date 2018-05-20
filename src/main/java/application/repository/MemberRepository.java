package application.repository;

import application.models.member.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {
    public Iterable<Member> findAllByUserName(String UserName);
    public Member findByUserName(String UserName);
}
