package alexgr.springmvc.service;

import alexgr.springmvc.model.UserEntity;
import alexgr.springmvc.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserEntity createUser(UserDTO userDTO);

    UserEntity updateUser(Integer id,UserDTO userDTO);

    void deleteUser(Integer id);

    List<UserEntity> findAllUsers();


    void makeAPurchase(Integer userId,Integer orderId);


}
