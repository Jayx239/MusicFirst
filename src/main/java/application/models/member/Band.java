package application.models.member;

import javax.persistence.Id;
import java.util.List;

public class Band extends Member {

    private String name;

    private List<Member> members;


}
