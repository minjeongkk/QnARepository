package qna.domain;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question")
    private List<Answer> answerList = new ArrayList<>();

    public void add(Answer answer) {
        answerList.add(answer);
    }

    public List<Answer> get() {
        return answerList;
    }

    public DeleteHistories delete() {
        DeleteHistories deleteHistories = new DeleteHistories();
        for (Answer answer : answerList) {
            answer.setDeleted(true);
            deleteHistories.addDeleteHistory(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        return deleteHistories;
    }

    public boolean contains(Answer answer) {
        return answerList.contains(answer);
    }
}
