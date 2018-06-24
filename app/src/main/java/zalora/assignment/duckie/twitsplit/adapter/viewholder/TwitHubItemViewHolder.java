package zalora.assignment.duckie.twitsplit.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import zalora.assignment.duckie.twitsplit.R;
import zalora.assignment.duckie.twitsplit.TwitSplitApplication;
import zalora.assignment.duckie.twitsplit.entity.Twit;
import zalora.assignment.duckie.twitsplit.entity.User;
import zalora.assignment.duckie.twitsplit.utility.Utils;

public class TwitHubItemViewHolder extends RecyclerView.ViewHolder {

    Twit twit;
    User user;

    SimpleDraweeView avatar;
    TextView name, tag, date, content;

    public TwitHubItemViewHolder(View itemView) {
        super(itemView);

        avatar = (SimpleDraweeView) itemView.findViewById(R.id.iv_avatar);
        name = (TextView) itemView.findViewById(R.id.tv_name);
        tag = (TextView) itemView.findViewById(R.id.tv_tag_name);
        date = (TextView) itemView.findViewById(R.id.tv_date);
        content = (TextView) itemView.findViewById(R.id.tv_content);
    }

    public void setData(Twit twit) {
        this.twit = twit;
        this.user = TwitSplitApplication.getUser();
        this.updateUI();
    }

    private void updateUI() {
        name.setText(user.name);
        tag.setText(user.tagName);
        date.setText(Utils.getPresentDateFromMilliseconds(twit.postDate));
        content.setText(twit.content);

        if (user.avatar == null) {
            avatar.setImageResource(R.drawable.ducgao_icon);
        }
        else {
            avatar.setImageURI(user.avatar);
        }
    }
}
