package zalora.assignment.duckie.twitsplit.core;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.AccountService;
import com.twitter.sdk.android.core.services.StatusesService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;
import zalora.assignment.duckie.twitsplit.TwitSplitApplication;
import zalora.assignment.duckie.twitsplit.core.interfaces.SimpleCallback;
import zalora.assignment.duckie.twitsplit.core.thread_excutor.ExecutorSupplier;

public class TwitterHelper {
    public static void fillCurrentUserData(final SimpleCallback callback) {
        TwitterApiClient apiClient = TwitterCore.getInstance().getApiClient();
        AccountService accountService = apiClient.getAccountService();

        final Call<User> userCall = accountService.verifyCredentials(false, false, false);
        userCall.enqueue(new Callback<User>() {
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

    public static void sendMessages(List<String> messages, final SimpleCallback callback) {
        usingThreadPool(messages, callback);
    }

    private static void usingThreadPool(List<String> messages, final SimpleCallback callback) {
        final ThreadPoolExecutor executor = ExecutorSupplier.getInstance().forBackgroundTasks();

        for (final String message : messages) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sendMessageSync(message);
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
            callback.onSuccess();
        } catch (InterruptedException e) {
            e.printStackTrace();
            callback.onFail();
        }
    }

    private static void sendMessageSync(String message) {
        TwitterApiClient apiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = apiClient.getStatusesService();
        final Call<Tweet> tweetCall = statusesService.update(message, null, null, null, null, null, null, null, null);

        try {
            /*
             * Response<Tweet> tweetResponse = tweetCall.execute();
             * I don't know what I need from this, so just call
             */
            tweetCall.execute();
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: try to catch it and remove all posted tweets.
        }
    }

    private static void sendMessageAsync(String message) {
        TwitterApiClient apiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = apiClient.getStatusesService();
        final Call<Tweet> tweetCall = statusesService.update(message, null, null, null, null, null, null, null, null);
        tweetCall.enqueue(new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                //TODO: I don't know what I need from this
            }

            @Override
            public void failure(TwitterException exception) {
                //TODO: try to catch it and remove all posted tweets.
            }
        });
    }
}
