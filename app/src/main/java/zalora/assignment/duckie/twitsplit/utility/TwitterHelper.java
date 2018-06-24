package zalora.assignment.duckie.twitsplit.utility;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.AccountService;

import retrofit2.Call;
import zalora.assignment.duckie.twitsplit.TwitSplitApplication;

public class TwitterHelper {
    public static void fillCurrentUserData(final SimpleCallback callback) {
        TwitterApiClient apiClient = TwitterCore.getInstance().getApiClient();
        AccountService accountService = apiClient.getAccountService();

        final Call<User> user = accountService.verifyCredentials(false, false, false);
        user.enqueue(new Callback<User>() {
            @Override
            public void success(Result<User> userResult) {
                zalora.assignment.duckie.twitsplit.entity.User user = new zalora.assignment.duckie.twitsplit.entity.User();
                user.name = userResult.data.screenName;
                user.tagName = "@" + userResult.data.name;
                user.avatar = userResult.data.profileImageUrl.replace("_normal", "");

                TwitSplitApplication.setUser(user);
                callback.onSuccess();
            }

            @Override
            public void failure(TwitterException exc) {
                callback.onFail();
            }
        });
    }
}
