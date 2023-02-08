package cd.bensmile.hoaxify.models;

import cd.bensmile.hoaxify.validators.UniqueUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "{hoaxify.constraints.username.NotNull.message}")
    @Size(min = 4, max = 255, message = "{hoaxify.constraints.username.Size.message}")
    @UniqueUsername
    private String username;
    @NotNull(message = "{hoaxify.constraints.displayName.NotNull.message}")
    @Size(min = 4, max = 255)
    private String displayName;
    @NotNull(message = "{hoaxify.constraints.password.NotNull.message}")
    @Size(min = 8, max = 255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{hoaxify.constraints.password.Pattern.message}")
    private String password;

}
