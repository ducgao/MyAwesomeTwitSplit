package zalora.assignment.duckie.twitsplit.mvp.presenter.new_twit;

public interface NewTwitModelPresenter {
    void postMessageSuccess();
    void postMessageError(int error);

    void startUploadMessage();
}

