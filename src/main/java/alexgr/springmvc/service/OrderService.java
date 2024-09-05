package alexgr.springmvc.service;

import alexgr.springmvc.model.OrderEntity;
import alexgr.springmvc.model.dto.OrderDTO;

public interface OrderService {

    OrderEntity createOrder(OrderDTO orderDTO);


    void deleteOrder(Integer id);

    OrderEntity findOrder(Integer id);
}
