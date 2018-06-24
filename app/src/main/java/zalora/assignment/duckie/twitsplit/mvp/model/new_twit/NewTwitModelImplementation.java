package zalora.assignment.duckie.twitsplit.mvp.model.new_twit;

import java.util.List;

import zalora.assignment.duckie.twitsplit.TwitSplitApplication;
import zalora.assignment.duckie.twitsplit.core.TwitterHelper;
import zalora.assignment.duckie.twitsplit.core.interfaces.SimpleCallback;
import zalora.assignment.duckie.twitsplit.core.repository.RealmHelperImplementation;
import zalora.assignment.duckie.twitsplit.core.repository.RepositoryHelper;
import zalora.assignment.duckie.twitsplit.core.twitsplit.TwitSplit;
import zalora.assignment.duckie.twitsplit.core.twitsplit.TwitSplitException;
import zalora.assignment.duckie.twitsplit.mvp.presenter.new_twit.NewTwitModelPresenter;

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
            postMessages(newMessages);
        } catch (TwitSplitException e) {
            this.presenter.postMessageError(e.getMessageID());
        }
    }

    private void postMessages(final List<String> messages) {
        if (TwitSplitApplication.isSimulation()) {
            repositoryHelper.addMessages(messages);
            this.presenter.postMessageSuccess();
        }
        else {
            this.presenter.startUploadMessage();
            TwitterHelper.sendMessages(messages, new SimpleCallback() {
                @Override
                public void onSuccess() {
                    repositoryHelper.addMessages(messages);
                    presenter.postMessageSuccess();
                }

                @Override
                public void onFail() {
                    //TODO: presenter.postMessageSuccess();
                }
            });
        }
    }
}
