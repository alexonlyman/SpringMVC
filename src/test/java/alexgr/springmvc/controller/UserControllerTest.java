package alexgr.springmvc.controller;

import alexgr.springmvc.model.UserEntity;
import alexgr.springmvc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @Test
    void getSummary_Success() throws Exception {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");
        user.setAddress("123 Street");

        List<UserEntity> users = List.of(user);

        when(userService.findAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users/summary")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname").value("John"))
                .andExpect(jsonPath("$[0].lastname").value("Doe"))
                .andExpect(jsonPath("$[0].email").doesNotExist())
                .andExpect(jsonPath("$[0].address").doesNotExist());
    }

    @Test
    void getDetails_Success() throws Exception {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");
        user.setAddress("123 Street");

        List<UserEntity> users = List.of(user);

        when(userService.findAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users/details")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname").value("John"))
                .andExpect(jsonPath("$[0].lastname").value("Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[0].address").value("123 Street"));
    }



}