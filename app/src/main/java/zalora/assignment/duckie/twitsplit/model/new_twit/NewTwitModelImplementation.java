package zalora.assignment.duckie.twitsplit.model.new_twit;

import java.util.List;

import zalora.assignment.duckie.twitsplit.presenter.new_twit.NewTwitModelPresenter;
import zalora.assignment.duckie.twitsplit.repository.RealmHelperImplementation;
import zalora.assignment.duckie.twitsplit.repository.RepositoryHelper;
import zalora.assignment.duckie.twitsplit.utility.twitsplit.TwitSplit;
import zalora.assignment.duckie.twitsplit.utility.twitsplit.TwitSplitException;

public class NewTwitModelImplementation implements NewTwitModel {
    NewTwitModelPresenter presenter;
    RepositoryHelper repositoryHelper = new RealmHelperImplementation();

    public NewTwitModelImplementation(NewTwitModelPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void postMessage(String content) {
        try {
            List<String> newMessages = TwitSplit.init(content).build();
            repositoryHelper.addMessages(newMessages);
            this.presenter.postMessageSuccess();
        } catch (TwitSplitException e) {
            this.presenter.postMessageError();
        }
    }
}
