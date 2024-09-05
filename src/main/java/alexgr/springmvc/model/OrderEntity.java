package alexgr.springmvc.model;

import alexgr.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orderInfo")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String product;
    private LocalDateTime dateTime;
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
