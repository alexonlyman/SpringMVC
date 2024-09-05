package alexgr.springmvc.model;

import alexgr.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonView(Views.UserSummary.class)
    private String firstname;
    @JsonView(Views.UserSummary.class)
    private String lastname;
    @JsonView(Views.UserDetails.class)
    private String email;
    @JsonView(Views.UserDetails.class)
    private String address;
    @JsonView(Views.UserDetails.class)
    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;
}
