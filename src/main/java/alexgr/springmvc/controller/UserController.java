package alexgr.springmvc.controller;

import alexgr.springmvc.model.UserEntity;
import alexgr.springmvc.model.dto.UserDTO;
import alexgr.springmvc.service.UserService;
import alexgr.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("пользователь создан");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("создание неудачно" + e.getMessage());
        }

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id, @RequestBody @Valid UserDTO userDTO) {
        UserEntity updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> findAllUsers() {
        List<UserEntity> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{userId}/purchase/{orderId}")
    public ResponseEntity<String> makeAPurchase(@PathVariable Integer userId, @PathVariable Integer orderId) {
        userService.makeAPurchase(userId, orderId);
        return ResponseEntity.ok("Покупка успешно совершена");
    }

    @GetMapping("/summary")
    @JsonView(Views.UserSummary.class)
    public List<UserEntity> getSummary() {
        return userService.findAllUsers();
    }

    @GetMapping("/details")
    @JsonView(Views.UserDetails.class)
    public List<UserEntity> getDetails() {
        return userService.findAllUsers();
    }
}
