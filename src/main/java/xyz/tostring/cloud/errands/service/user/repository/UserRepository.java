package xyz.tostring.cloud.errands.service.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import xyz.tostring.cloud.errands.service.user.entity.UserDO;

@Repository
public interface UserRepository extends JpaRepository<UserDO, String > , JpaSpecificationExecutor<UserDO> {
}
