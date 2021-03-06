package zalora.assignment.duckie.twitsplit.mvp.presenter.twit_hub;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface TwitHubViewPresenter {
    void loadTwits();
    void logout();

    void integrateRecycleView(RecyclerView recycleView);

    View.OnClickListener getPostButtonClickHandler();
}
