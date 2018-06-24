package zalora.assignment.duckie.twitsplit.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import zalora.assignment.duckie.twitsplit.TwitSplitApplication;
import zalora.assignment.duckie.twitsplit.entity.Twit;
import zalora.assignment.duckie.twitsplit.entity.User;

public class RealmHelperImplementation implements RepositoryHelper {
    Realm realm = Realm.getDefaultInstance();

    @Override
    public void addMessages(List<String> messages) {
        List<Twit> twits = new ArrayList<>();

        Number currentMaxID = realm.where(Twit.class).max("id");
        currentMaxID = currentMaxID == null ? 0 : currentMaxID;

        int twitsSize = messages.size();
        for (int i = 0; i < twitsSize; i++ ) {
            Twit twit = new Twit();
            twit.id = currentMaxID.longValue() + i + 1;
            twit.postDate = System.currentTimeMillis();
            twit.content = messages.get(i);

            twits.add(twit);
        }

        realm.beginTransaction();
        realm.copyToRealm(twits);
        realm.commitTransaction();
    }
}
