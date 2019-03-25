package xyz.tostring.cloud.errands.service.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import xyz.tostring.cloud.errands.service.user.entity.UserReceiveInfoDO;

import java.util.List;

@Repository
public interface UserReceiveInfoRepository extends JpaRepository<UserReceiveInfoDO, Long> , JpaSpecificationExecutor<UserReceiveInfoDO> {
    List<UserReceiveInfoDO> findAllByUserOpenIdOrderBySortWeightDescUpdateTimeDesc(String userOpenId);
}
