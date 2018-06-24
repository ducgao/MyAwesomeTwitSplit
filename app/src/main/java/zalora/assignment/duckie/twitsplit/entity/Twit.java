package zalora.assignment.duckie.twitsplit.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Twit extends RealmObject {
    @PrimaryKey
    public long id;

    public long postDate;
    public String content;
    public String name;
    public String tagName;


    public Twit() {}
}
