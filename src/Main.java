import java.io.File;
import java.util.Scanner;

/**
 * Created by Salomon on 2015/08/14.
 */
public class Main
{
    private static FeedManager feedManager;

    public static void main(String[] args)
    {
        /*
        Since additional functionality might be added in the future,
        it's a good idea to encapsulate all the feed functionality inside a class.
        In this case the FeedManager class.
         */

        feedManager = new FeedManager();

        readUsers(args[0]);
        readTweets(args[1]);

        feedManager.displayFeed();
    }

    private static void readUsers(String filename)
    {
        try
        {
            File file = new File(filename);

            if (!file.exists())
                throw new Exception(String.format("Cannot find the file \"%s\".", filename));

            Scanner in = new Scanner(file);

            while (in.hasNextLine())
            {
                String[] line = in.nextLine().split(" follows ");

                if (line.length < 2)
                    throw new Exception(String.format("Invalid format in \"%s\". Format should be: <user> follows <user>[, <user]*.", filename));

                User user = feedManager.add(line[0]);

                String[] follows = line[1].split(", ");

                for (String s : follows)
                {
                    User follow = feedManager.add(s);
                    follow.addFollowing(user);
                }
            }

            in.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void readTweets(String filename)
    {
        try
        {
            File file = new File(filename);

            if (!file.exists())
                throw new Exception(String.format("Cannot find the file \"%s\".", filename));

            Scanner in = new Scanner(file);

            while (in.hasNextLine())
            {
                String[] line = in.nextLine().split("> ");

                if (line.length < 2)
                    throw new Exception(String.format("Invalid format in \"%s\". Format should be: <user>> <message>.", filename));

                feedManager.tweet(line[0],line[1]);
            }

            in.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

