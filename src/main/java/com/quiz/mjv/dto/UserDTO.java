package com.quiz.mjv.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter  @NoArgsConstructor @AllArgsConstructor @ToString
public class UserDTO {
    private Long id;
    @NotBlank(message = "O nickname não pode estar em branco")
    private String nickname;
    @NotBlank
    @Email(regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String email;
    @NotBlank
    @Size(min = 6)
    private String password;
    private int score;

}
