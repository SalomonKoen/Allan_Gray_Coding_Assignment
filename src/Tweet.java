/**
 * Created by Salomon on 2015/08/14.
 */
public class Tweet
{
    //User in stead of String so as to store a pointer rather than another copy of the user's name
    private User user;
    private String message;

    public Tweet(User user, String message)
    {
        this.user = user;
        this.message = message;
    }

    public User getUser()
    {
        return user;
    }

    public String getMessage()
    {
        return message;
    }
}
