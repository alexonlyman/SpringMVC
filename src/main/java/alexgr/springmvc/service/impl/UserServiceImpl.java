package alexgr.springmvc.service.impl;

import alexgr.springmvc.mapper.UserMapper;
import alexgr.springmvc.model.OrderEntity;
import alexgr.springmvc.model.UserEntity;
import alexgr.springmvc.model.dto.UserDTO;
import alexgr.springmvc.repository.OrderRepo;
import alexgr.springmvc.repository.UserRepo;
import alexgr.springmvc.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper mapper;
    private final OrderRepo orderRepo;

    @Override
    public UserEntity createUser(@Valid UserDTO userDTO) {
        UserDTO user = new UserDTO(userDTO.firstname(), userDTO.lastname(), userDTO.address(), userDTO.email());
        return userRepo.save(mapper.toEntity(user));

    }

    @Override
    public UserEntity updateUser(Integer id,@Valid UserDTO userDTO) {
        UserEntity user = userRepo.findUserEntityById(id).orElseThrow(() -> new RuntimeException("user not found"));
        user.setFirstname(userDTO.firstname());
        user.setLastname(userDTO.lastname());
        user.setAddress(userDTO.address());
        user.setEmail(userDTO.email());
        return userRepo.save(user);
    }


    @Override
    public void deleteUser(Integer id) {
        userRepo.deleteById(id);

    }

    @Override
    public List<UserEntity> findAllUsers() {
       return userRepo.findAll();
    }


    @Override
    public void makeAPurchase(Integer userId,Integer orderId) {
        UserEntity user = userRepo.findUserEntityById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        OrderEntity order = orderRepo.findOrderEntitiesById(orderId).orElseThrow(() -> new RuntimeException("user not found"));
        order.setUser(user);
        if (user.getOrders() == null) {
            user.setOrders(new LinkedList<>()); // Инициализируем пустой список, если он null
        }
        user.getOrders().add(order);
        userRepo.save(user);
        orderRepo.save(order);
    }

}
