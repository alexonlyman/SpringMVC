package alexgr.springmvc.service.impl;

import alexgr.springmvc.mapper.OrderMapper;
import alexgr.springmvc.model.OrderEntity;
import alexgr.springmvc.model.dto.OrderDTO;
import alexgr.springmvc.repository.OrderRepo;
import alexgr.springmvc.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final OrderMapper mapper;

    @Override
    public OrderEntity createOrder(@Valid OrderDTO orderDTO) {
        OrderDTO order = new OrderDTO(orderDTO.product(), orderDTO.dateTime(), orderDTO.status());
        return orderRepo.save(mapper.toEntity(order));
    }


    @Override
    public void deleteOrder(Integer id) {
        orderRepo.deleteById(id);
    }

    @Override
    public OrderEntity findOrder(Integer id) {
       return orderRepo.findOrderEntitiesById(id).orElseThrow(() -> new RuntimeException("orderNotFound"));
    }
}
