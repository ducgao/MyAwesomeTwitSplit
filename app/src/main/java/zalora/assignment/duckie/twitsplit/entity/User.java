package zalora.assignment.duckie.twitsplit.entity;

public class User {
    public String name;
    public String tagName;

    public User() {}

    public static User getFakeUser() {
        User user = new User();
        user.name = "Nguyen Van Duc";
        user.tagName = "@Ducgao4213";

        return user;
    }
}
