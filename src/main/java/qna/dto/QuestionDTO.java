package qna.dto;

import lombok.Builder;
import lombok.Getter;
import qna.domain.Question;
import qna.domain.User;

import javax.persistence.*;

@Builder
@Getter
public class QuestionDTO {
    private Long id;
    private String title;
    private String contents;
    private Long userId;

    public QuestionDTO() {
    }

    public QuestionDTO(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public QuestionDTO(Long id, String title, String contents, Long userId) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
    }

    public void setUser(Long userId){
        this.userId = userId;
    }

    public Question toEntity(){
        return Question.builder()
                .id(id)
                .title(title)
                .contents(contents)
                .build();
    }
}
