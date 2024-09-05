package alexgr.springmvc.repository;

import alexgr.springmvc.model.OrderEntity;
import alexgr.springmvc.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {
    Optional<OrderEntity> findOrderEntitiesById(Integer id);


}
