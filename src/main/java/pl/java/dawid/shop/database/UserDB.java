package pl.java.dawid.shop.database;

import pl.java.dawid.shop.model.User;

import javax.swing.text.html.Option;
import java.util.ArrayList;

public class UserDB {
    private final ArrayList<User> users = new ArrayList<User>();

    private static final UserDB instance = new UserDB();

    private UserDB() {
        User u1 = new User("admin","eb0468abcd9f88e9844fd140fbb6acff", User.Role.ADMIN);
        this.users.add(u1);
        this.users.add( new User("janusz",
                "6fff9bb96e12805ea3ccb8ec27e8206f", User.Role.USER));
    }

    public User findByLogin(String login) {
        java.util.Optional<User> ret = users.stream()
                .filter(user -> user.getLogin().equals(login)).findFirst();
        if(ret.isEmpty())
            return null;
        return ret.get();

    }

    public static UserDB getInstance() {
        return instance;
    }
}
