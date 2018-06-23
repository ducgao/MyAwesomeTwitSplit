package zalora.assignment.duckie.twitsplit.presenter.twit_hub;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface TwitHubViewPresenter {
    void loadTwits();
    void integrateRecycleView(RecyclerView recycleView);
    View.OnClickListener getPostButtonClickHandler();
}
