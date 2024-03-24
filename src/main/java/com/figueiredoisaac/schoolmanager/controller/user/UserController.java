package com.figueiredoisaac.schoolmanager.controller.user;

import com.figueiredoisaac.schoolmanager.commons.UserRoles;
import com.figueiredoisaac.schoolmanager.domain.user.model.User;
import com.figueiredoisaac.schoolmanager.domain.user.usecase.UserUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserUseCase useCase;

    public UserController(UserUseCase useCase) { this.useCase = useCase;}


    @GetMapping(value = "/{username}")
    public ResponseEntity<UserFindOutput> findByUsername(
            @PathVariable("username") String username
    ) throws Exception
    {
        User user = useCase.findByUsername(username);
        return new ResponseEntity(
                new UserFindOutput(
                        user.getName(),
                        user.getUsername(),
                        user.getEmail()
                ),
                HttpStatus.OK);
    }


    @PostMapping(value = "/register")
    public ResponseEntity create(
            @Valid @RequestBody UserCreateInput input
    ) throws Exception {
           User user = useCase.create(input.toModel());
        return new ResponseEntity(
                new UserCreateOutput(
                        user.getName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole(),
                        user.getCreatedAt()
                ),
                HttpStatus.CREATED);
    }
}

class UserFindOutput {
    private String name;
    private String username;
    private String email;

    public UserFindOutput(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserFindOutput{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

class UserCreateInput {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Size(max = 20, message = "O campo deve ter no máximo 20 caracteres")
    @Pattern(regexp = "^[a-z](?:[a-z\\-]*[a-z])?$", message = "Username deve conter apenas caracteres minúsculos e '-' usado como separador ")
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;

    private UserRoles role;

    private LocalDateTime createdAt;

    public UserCreateInput(String name, String username, String email, String password, UserRoles role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }

    public User toModel() {
        return new User(
                this.id,
                this.name,
                this.username,
                this.email,
                this.password,
                this.role,
                this.createdAt
        );
    }
}
class UserCreateOutput {

    private String name;
    private String username;
    private String email;
    private UserRoles role;
    private LocalDateTime createdAt;

    public UserCreateOutput(String name, String username, String email,
                            UserRoles role, LocalDateTime createdAt) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UserRoles getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}