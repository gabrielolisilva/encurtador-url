package gabrielsilva.dev.entity;

import gabrielsilva.dev.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String login;

    @Column(unique = true)
    private String email;

    @Column
    private String senha;

    @Column(name = "role")
    private UserRoleEnum role;
}
