package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistoryList = new ArrayList<>();

    public void addDeleteHistory(DeleteHistory deleteHistory) {
        deleteHistoryList.add(deleteHistory);
    }

    public void addDeleteHistories(DeleteHistories deleteHistories) {
        deleteHistoryList.addAll(deleteHistories.deleteHistoryList);
    }

    public List<DeleteHistory> getHistories() {
        return deleteHistoryList;
    }
}
