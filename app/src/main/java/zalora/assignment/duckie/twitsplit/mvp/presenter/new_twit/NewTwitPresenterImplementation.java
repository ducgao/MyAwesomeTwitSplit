package zalora.assignment.duckie.twitsplit.mvp.presenter.new_twit;

import zalora.assignment.duckie.twitsplit.mvp.model.new_twit.NewTwitModel;
import zalora.assignment.duckie.twitsplit.mvp.model.new_twit.NewTwitModelImplementation;
import zalora.assignment.duckie.twitsplit.mvp.view.new_twit.NewTwitView;

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
    public void postMessageError(int error) {
        this.view.showInputError(error);
    }

    @Override
    public void startUploadMessage() {
        this.view.showLoading();
    }

    @Override
    public void postMessageSuccess() {
        this.view.closeInstance();
    }
}
