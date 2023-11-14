package qna.domain;

import qna.CannotDeleteException;
import qna.ErrorMessage;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String contents;
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    @ManyToOne
    private User writer;
    @Column
    private boolean deleted = false;
    @Embedded
    private Answers answerList = new Answers();

    public Question(String title, String contents) {
        this(null, title, contents);
    }

    public Question(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public Question() {

    }

    public Question writeBy(User writer) {
        this.writer = writer;
        return this;
    }

    public void isOwner(User writer) throws CannotDeleteException {
        if (!this.writer.equals(writer)) {
            throw new CannotDeleteException(ErrorMessage.CHECK_QUESTION_AUTH);
        }
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);

        if (answer.getQuestion() != this) {
            answer.setQuestion(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Answers getAnswerList() {
        return answerList;
    }

    public void setAnswerList(Answers answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", writer=" + writer +
                ", deleted=" + deleted +
                '}';
    }

    public DeleteHistories deleteQuestion(User loginUser) throws CannotDeleteException {
        isOwner(loginUser);

        for (Answer answer : this.answerList.get()) {
            answer.isOwner(loginUser);
        }

        return delete();
    }

    public DeleteHistories delete() {
        DeleteHistories deleteHistories = new DeleteHistories();
        setDeleted(true);
        deleteHistories.addDeleteHistory(new DeleteHistory(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now()));
        deleteHistories.addDeleteHistories(this.answerList.delete());

        return deleteHistories;
    }
}
