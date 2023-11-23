package qna.dto;

import lombok.Builder;
import lombok.Getter;
import qna.domain.Answer;

@Builder
@Getter
public class AnswerDTO {
    private Long id;
    private String contents;
    private Long userId;
    private Long questionId;

    public AnswerDTO(Long id, String contents, Long userId, Long questionId) {
        this.id = id;
        this.contents = contents;
        this.userId = userId;
        this.questionId = questionId;
    }

    public Answer toEntity(){
        return Answer.builder()
                .contents(contents)
                .build();
    }
}
