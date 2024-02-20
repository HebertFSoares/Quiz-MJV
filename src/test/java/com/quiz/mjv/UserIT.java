package com.quiz.mjv;

import com.quiz.mjv.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/users/users-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/users/users-delete.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class UserIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void createdUser_UserAndPassword_Return201(){
        UserDTO responseBody = testClient
                .post()
                .uri("api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserDTO(10L, "jinks", "jick@email.com", "123456", 35))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserDTO.class)
                .returnResult().getResponseBody();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getId()).isNotNull();
        assertThat(responseBody.getNickname()).isEqualTo("jinks");
        assertThat(responseBody.getEmail()).isEqualTo("jick@email.com");
        assertThat(responseBody.getPassword()).isEqualTo("123456");

    }
}
