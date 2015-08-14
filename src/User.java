import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Salomon on 2015/08/14.
 */
public class User implements Comparable<User>
{
    private String name;
    private HashMap<String, User> followers = new HashMap<String, User>();

    //Store pointer to tweet rather than duplicating tweet messages for each user
    private List<Tweet> tweets = new ArrayList<Tweet>();

    public User(String name, FeedManager manager)
    {
        this.name = name;
    }

    //Add tweet to list and update followers' tweets list
    public void tweet(String message)
    {
        Tweet newTweet = new Tweet(this, message);
        tweets.add(newTweet);

        for (User user : followers.values())
        {
            user.tweets.add(newTweet);
        }
    }

    public void addFollowing(User user)
    {
        if (!followers.containsKey(user.name))
        {
            followers.put(user.name, user);
        }
    }

    @Override
    public int compareTo(User o)
    {
        return this.name.compareTo(o.name);
    }

    public String getName()
    {
        return this.name;
    }

    //Display's user's name as well as related tweets
    public void displayUser()
    {
        System.out.println(name);

        for (Tweet tweet : tweets)
        {
            System.out.printf("\t@%s: %s\n", tweet.getUser().name, tweet.getMessage());
        }
    }
}
