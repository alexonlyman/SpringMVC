package alexgr.springmvc.repository;

import alexgr.springmvc.model.OrderEntity;
import alexgr.springmvc.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findUserEntityById(Integer id);


}
