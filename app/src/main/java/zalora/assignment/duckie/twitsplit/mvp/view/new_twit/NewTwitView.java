package zalora.assignment.duckie.twitsplit.mvp.view.new_twit;

public interface NewTwitView {
    void closeInstance();
    void showInputError(int error);

    void showLoading();
    void hideLoading();
}
