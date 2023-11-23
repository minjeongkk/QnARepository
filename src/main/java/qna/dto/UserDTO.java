package qna.dto;

import lombok.Builder;
import lombok.Getter;
import qna.domain.User;
@Builder
@Getter
public class UserDTO {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public UserDTO(Long id, String userId, String password, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User toEntity(){
        return User.builder()
                .id(this.id)
                .userId(this.userId)
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .build();
    }
}
