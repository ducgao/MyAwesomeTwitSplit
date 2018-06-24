package zalora.assignment.duckie.twitsplit.mvp.model.twit_hub;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;
import zalora.assignment.duckie.twitsplit.entity.Twit;
import zalora.assignment.duckie.twitsplit.mvp.presenter.twit_hub.TwitHubModelPresenter;

public class TwitHubModelImplementation implements TwitHubModel {

    TwitHubModelPresenter presenter;

    List<Twit> twits;

    public TwitHubModelImplementation(TwitHubModelPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadTwits() {
        this.twits = Realm.getDefaultInstance().where(Twit.class).findAll().sort("id", Sort.DESCENDING);
        this.presenter.loadTwitsCompleted(twits);
    }
}
