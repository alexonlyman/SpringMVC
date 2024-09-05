package alexgr.springmvc.service.impl;

import alexgr.springmvc.mapper.UserMapper;
import alexgr.springmvc.model.OrderEntity;
import alexgr.springmvc.model.UserEntity;
import alexgr.springmvc.model.dto.UserDTO;
import alexgr.springmvc.repository.OrderRepo;
import alexgr.springmvc.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_Success() {

        UserDTO userDTO = new UserDTO("John", "Doe", "123 Street", "john.doe@example.com");
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname(userDTO.firstname());
        userEntity.setLastname(userDTO.lastname());
        userEntity.setAddress(userDTO.address());
        userEntity.setEmail(userDTO.email());

        when(mapper.toEntity(any(UserDTO.class))).thenReturn(userEntity);
        when(userRepo.save(any(UserEntity.class))).thenReturn(userEntity);

        UserEntity result = userService.createUser(userDTO);

        assertEquals(userDTO.firstname(), result.getFirstname());
        assertEquals(userDTO.lastname(), result.getLastname());
        assertEquals(userDTO.address(), result.getAddress());
        assertEquals(userDTO.email(), result.getEmail());
    }

    @Test
    void updateUser_Success() {
        // Arrange
        Integer userId = 1;
        UserDTO userDTO = new UserDTO("Jane", "Doe", "456 Avenue", "jane.doe@example.com");
        UserEntity existingUser = new UserEntity();
        existingUser.setId(userId);
        existingUser.setFirstname("John");
        existingUser.setLastname("Doe");
        existingUser.setAddress("123 Street");
        existingUser.setEmail("john.doe@example.com");

        when(userRepo.findUserEntityById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepo.save(any(UserEntity.class))).thenReturn(existingUser);

        // Act
        UserEntity updatedUser = userService.updateUser(userId, userDTO);

        // Assert
        assertEquals(userDTO.firstname(), updatedUser.getFirstname());
        assertEquals(userDTO.lastname(), updatedUser.getLastname());
        assertEquals(userDTO.address(), updatedUser.getAddress());
        assertEquals(userDTO.email(), updatedUser.getEmail());
    }

    @Test
    void deleteUser_Success() {
        // Arrange
        Integer userId = 1;

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepo, times(1)).deleteById(userId);
    }
    @Test
    void findAllUsers_Success() {
        // Arrange
        UserEntity user1 = new UserEntity();
        user1.setFirstname("John");
        user1.setLastname("Doe");

        UserEntity user2 = new UserEntity();
        user2.setFirstname("Jane");
        user2.setLastname("Doe");

        List<UserEntity> users = Arrays.asList(user1, user2);

        when(userRepo.findAll()).thenReturn(users);

        // Act
        List<UserEntity> result = userService.findAllUsers();

        // Assert
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstname());
        assertEquals("Jane", result.get(1).getFirstname());
    }

    @Test
    void makeAPurchase_Success() {
        // Arrange
        Integer userId = 1;
        Integer orderId = 10;

        UserEntity user = new UserEntity();
        user.setId(userId);

        OrderEntity order = new OrderEntity();
        order.setId(orderId);

        when(userRepo.findUserEntityById(userId)).thenReturn(Optional.of(user));
        when(orderRepo.findOrderEntitiesById(orderId)).thenReturn(Optional.of(order));

        // Act
        userService.makeAPurchase(userId, orderId);

        // Assert
        assertEquals(user, order.getUser());
        verify(userRepo, times(1)).save(user);
        verify(orderRepo, times(1)).save(order);
    }



}