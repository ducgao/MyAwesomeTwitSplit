package zalora.assignment.duckie.twitsplit.repository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import zalora.assignment.duckie.twitsplit.entity.Twit;
import zalora.assignment.duckie.twitsplit.entity.User;

public class RealmHelperImplementation implements RepositoryHelper {
    Realm realm = Realm.getDefaultInstance();

    @Override
    public void addMessages(List<String> messages) {
        List<Twit> twits = new ArrayList<>();
        for (String message: messages) {
            Twit twit = new Twit();
            twit.postDate = System.currentTimeMillis();
            twit.content = message;
            twit.name = User.getFakeUser().name;
            twit.tagName = User.getFakeUser().tagName;

            twits.add(twit);
        }

        realm.beginTransaction();
        realm.copyToRealm(twits);
        realm.commitTransaction();
    }
}
