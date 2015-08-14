import java.util.TreeMap;

/**
 * Created by Salomon on 2015/08/14.
 */
public class FeedManager
{
    private TreeMap<String, User> users = new TreeMap<String, User>();

    public FeedManager()
    {

    }

    /*
    If user exists then it returns the existing user,
    if not a new user is added and returned
    */
    public User add(String name)
    {
        User user = users.get(name);

        if (user == null)
        {
            user = new User(name, this);
            users.put(name, user);
        }

        return user;
    }

    public void tweet(String username, String tweet)
    {
        try
        {
            if (tweet.length() > 140)
                throw new Exception(String.format("%s's tweet has exceeded 140 characters.",username));

            User user = users.get(username);

            if (user == null)
                throw new Exception(String.format("User with username \"%s\" does not exist.",username));

            user.tweet(tweet);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void displayFeed()
    {
        for (User user : users.values())
        {
            user.displayUser();
        }
    }
}
