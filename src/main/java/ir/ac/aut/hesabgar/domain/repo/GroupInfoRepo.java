package ir.ac.aut.hesabgar.domain.repo;

import ir.ac.aut.hesabgar.domain.document.GroupInfo;
import ir.ac.aut.hesabgar.domain.document.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupInfoRepo extends MongoRepository<GroupInfo, String> {
    GroupInfo getGroupInfoById(String id);
}
