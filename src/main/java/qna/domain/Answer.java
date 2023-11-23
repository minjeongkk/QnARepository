package qna.domain;

import lombok.Builder;
import qna.CannotDeleteException;
import qna.ErrorMessage;
import qna.NotFoundException;
import qna.UnAuthorizedException;
import qna.dto.AnswerDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Builder
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;
    @Lob
    private String contents;
    @Column
    private boolean deleted = false;

    public Answer(User writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, User writer, Question question, String contents) {
        this.id = id;

        if (Objects.isNull(writer)) {
            throw new UnAuthorizedException();
        }

        if (Objects.isNull(question)) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public Answer() {

    }

    public Answer(Long id, User writer, Question question, String contents, boolean deleted) {
        this.id = id;
        this.writer = writer;
        this.question = question;
        this.contents = contents;
        this.deleted = deleted;
    }

    public void isOwner(User loginUser) throws CannotDeleteException {
        if (!this.writer.equals(loginUser)||!this.writer.equals(this.question.getWriter())) {
            throw new CannotDeleteException(ErrorMessage.CHECK_ANSWER_AUTH);
        }
    }

    public void toQuestion(Question question) {
        this.question = question;

        if (!this.question.getAnswerList().contains(this)){
            this.question.getAnswerList().add(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", writerId=" + writer +
                ", question=" + question +
                ", contents='" + contents + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    public AnswerDTO toDTO(){
        return AnswerDTO.builder()
                .id(id)
                .contents(contents)
                .questionId(question.getId())
                .userId(writer.getId())
                .build();
    }
}
