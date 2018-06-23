package zalora.assignment.duckie.twitsplit.presenter.twit_hub;

import java.util.List;

import zalora.assignment.duckie.twitsplit.entity.Twit;

public interface TwitHubModelPresenter {
    void loadTwitsCompleted(List<Twit> twits);
}
