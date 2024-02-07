package com.quiz.mjv.dto;

import lombok.*;

@Getter @Setter  @NoArgsConstructor @AllArgsConstructor @ToString
public class UserDTO {
    private Long id;
    private String nickname;
    private String email;
    private String password;
}
