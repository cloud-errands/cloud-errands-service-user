package xyz.tostring.cloud.errands.service.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.tostring.cloud.errands.service.user.entity.UserReceiveInfoDO;

import java.util.List;

@Repository
public interface UserReceiveInfoDao extends JpaRepository<UserReceiveInfoDO, Long> {
    List<UserReceiveInfoDO> findAllByUserOpenIdOrderBySortWeightDescUpdateTimeDesc(String userOpenId);
}
