package org.sergio.library.domain.security;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "users")
public class SecUser {
    @Id
    @Indexed
    private String id;

    private String login;

    private String password;

    private String role;

}
