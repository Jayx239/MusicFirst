package application.config;

import application.models.MemberRole;
import application.models.SecurityRole;
import application.repository.MemberRoleRepository;
import application.repository.SecurityRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    public DataLoader(MemberRoleRepository memberRoleRepository, SecurityRoleRepository securityRoleRepository) {
        this.memberRoleRepository = memberRoleRepository;
        this.securityRoleRepository = securityRoleRepository;
    }

    private MemberRoleRepository memberRoleRepository;
    private SecurityRoleRepository securityRoleRepository;

    public void run(ApplicationArguments args) {

        /* Setup member roles */
        MemberRole musicianRole = new MemberRole("MUSICIAN");
        MemberRole producerRole = new MemberRole("PRODUCER");
        MemberRole managerRole = new MemberRole("MANAGER");
        MemberRole userRole = new MemberRole("USER");
        MemberRole guestRole = new MemberRole("GUEST");

        memberRoleRepository.save(musicianRole);
        memberRoleRepository.save(producerRole);
        memberRoleRepository.save(managerRole);
        memberRoleRepository.save(userRole);
        memberRoleRepository.save(guestRole);

        /* Setup security roles */
        SecurityRole standardRole = new SecurityRole("STANDARD");
        SecurityRole dbaRole = new SecurityRole("DBA");
        SecurityRole adminRole = new SecurityRole("ADMIN");

        securityRoleRepository.save(standardRole);
        securityRoleRepository.save(dbaRole);
        securityRoleRepository.save(adminRole);

    }
}