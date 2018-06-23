package zalora.assignment.duckie.twitsplit.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.entity.Twit;
import zalora.assignment.duckie.twitsplit.utility.Utils;

public class TwitHubItemViewHolder extends RecyclerView.ViewHolder {

    Twit twit;

    ImageView avatar;
    TextView name, tag, date, content;

    public TwitHubItemViewHolder(View itemView) {
        super(itemView);

        avatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
        name = (TextView) itemView.findViewById(R.id.tv_name);
        tag = (TextView) itemView.findViewById(R.id.tv_tag_name);
        date = (TextView) itemView.findViewById(R.id.tv_date);
        content = (TextView) itemView.findViewById(R.id.tv_content);
    }

    public void setData(Twit twit) {
        this.twit = twit;
        this.updateUI();
    }

    private void updateUI() {
        name.setText(twit.name);
        tag.setText(twit.tagName);
        date.setText(Utils.getPresentDateFromMilliseconds(twit.postDate));
        content.setText(twit.content);
    }
}
