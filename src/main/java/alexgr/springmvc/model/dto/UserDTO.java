package alexgr.springmvc.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @Size(min = 5, max = 15)
        String firstname,
        @Size(min = 5, max = 15)
        String lastname,
        @Size(max = 30)
        String address,
        @Email
        String email) {

}
