package alexgr.springmvc.model.dto;

import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record OrderDTO(
        @Size(max = 30)
        String product,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime dateTime,
        String status) {

}
