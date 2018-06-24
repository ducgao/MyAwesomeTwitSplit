package zalora.assignment.duckie.twitsplit.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.adapter.viewholder.TwitHubItemViewHolder;
import zalora.assignment.duckie.twitsplit.entity.Twit;

public class TwitHubAdapter extends RecyclerView.Adapter<TwitHubItemViewHolder> {

    List<Twit> twits;

    @NonNull
    @Override
    public TwitHubItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_twit_item, parent, false);

        return new TwitHubItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TwitHubItemViewHolder holder, int position) {
        Twit twit = twits.get(position);
        holder.setData(twit);
    }

    @Override
    public int getItemCount() {
        return twits.size();
    }

    public void setNewData(List<Twit> twits) {
        this.twits = twits;
        this.notifyDataSetChanged();
    }
}
