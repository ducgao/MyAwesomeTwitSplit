package zalora.assignment.duckie.twitsplit.presenter.new_twit;

import android.view.View;

import zalora.assignment.duckie.twitsplit.model.new_twit.NewTwitModel;
import zalora.assignment.duckie.twitsplit.model.new_twit.NewTwitModelImplementation;
import zalora.assignment.duckie.twitsplit.view.new_twit.NewTwitView;

public class NewTwitPresenterImplementation implements NewTwitViewPresenter, NewTwitModelPresenter {

    NewTwitView view;
    NewTwitModel model;

    public NewTwitPresenterImplementation(NewTwitView view) {
        this.view = view;
        this.model = new NewTwitModelImplementation(this);
    }

    @Override
    public void postMessage(String content) {
        this.model.postMessage(content);
    }

    @Override
    public void postMessageError() {
//TODO
    }

    @Override
    public void postMessageSuccess() {
        this.view.closeInstance();
    }
}
