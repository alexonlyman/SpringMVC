package alexgr.springmvc.mapper;

import alexgr.springmvc.model.OrderEntity;
import alexgr.springmvc.model.UserEntity;
import alexgr.springmvc.model.dto.OrderDTO;
import alexgr.springmvc.model.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMapper {
    private final ObjectMapper mapper;

    public OrderEntity toEntity(OrderDTO orderDTO) {
        return mapper.convertValue(orderDTO, OrderEntity.class);
    }

    public OrderDTO toDto(OrderEntity orderEntity) {
        return mapper.convertValue(orderEntity, OrderDTO.class);
    }
}
