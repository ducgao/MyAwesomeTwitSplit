package zalora.assignment.duckie.twitsplit.mvp.view.login;

public interface LoginView {
    void moveToTwitHub();

    void showLoading();
    void hideLoading();

    void showError(int error);
}
