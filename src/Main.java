import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.*;


public class Main {

    public static List<Status> tweets1 = new ArrayList<>();
    public static List<Status> tweets2 = new ArrayList<>();
    public static int pageno1 = 1;
    public static int pageno2 = 1;

    public static void main(String[] args) {

        // initializing the twitter API
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("D68xw3fiaR17GcsGh7q9mbWYO")
                .setOAuthConsumerSecret("ljpNGf6OV8lm72eCarVdU7B8zxH19JvPkbJiI2RWMgtrBnAlHW")
                .setOAuthAccessToken("1159149210291146752-f25YLQlP4GMmiLatNmYM5MvYAZN3iZ")
                .setOAuthAccessTokenSecret("s2u8ef2VdugW8vfgIl7DwjLnnb2She7AEiy8uExtVDovw");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        String user1 = "elonmusk";
        String user2 = "kanyewest";


        // gets the tweet from Elon Musk
        while (true) {
            try {
                int size = tweets1.size();
                Paging page = new Paging(pageno1++, 100);
                tweets1.addAll(twitter.getUserTimeline(user1, page));
                if (tweets1.size() == size)
                    break;
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }


        // gets the tweet from Kanye West
        while (true) {
            try {
                int size = tweets2.size();
                Paging page = new Paging(pageno2++, 100);
                tweets2.addAll(twitter.getUserTimeline(user2, page));
                if (tweets2.size() == size)
                    break;
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }


        // main body of game, checks if user wants to play again
        String tryAgain = "firstAttempt";
        while (tryAgain.equals("Y") || tryAgain.equals("y") || tryAgain.equals("firstAttempt")) {

            //scans the user input
            Scanner scnr = new Scanner(System.in);

            int totalpt = 0;     // total correct answers by the user
            int totalgames = 0;  // total games the user played in 1 round
            int outerbuffer;     // random number 1
            int innerbuffer;     // random number 2

            System.out.println("Welcome to the Guess Tweet game!");
            System.out.println("Press <F> to start");
            String start = scnr.nextLine();
            String end = "initial";

            // check if user can read text
            while (!start.equals("F") && !start.equals("f")) {
                System.out.println("Please type the correct character <F>");
                start = scnr.nextLine();
            }


            // main body of game, check if user wants to end the current game round
            while (!end.equals("stop")) {

                // randomly choose a tweet's owner: if random to 1 then Elon Muck; 2 then Kanye West
                outerbuffer = (Math.random() <= 0.5) ? 1 : 2;

                // when the tweet is by Elon Musk
                if (outerbuffer == 1) {
                    // randomly choose a tweet by Elon Musk
                    innerbuffer = (int) (Math.random() * tweets1.size());

                    // check if the tweet does not contain references to other users or URLs
                    while (tweets1.get(innerbuffer).getText().indexOf('@') != -1
                            || tweets1.get(innerbuffer).getText().toLowerCase().contains("https")) {
                        innerbuffer = (int) (Math.random() * tweets1.size());
                    }

                    // simple formatting of the tweet displayed
                    for (int i = 0; i < tweets1.get(innerbuffer).getText().length(); ++i) {
                        System.out.print("*");
                    }
                    System.out.println();
                    System.out.println(tweets1.get(innerbuffer).getText());
                    for (int i = 0; i < tweets1.get(innerbuffer).getText().length(); ++i) {
                        System.out.print("*");
                    }
                    System.out.println();

                    // ask question
                    System.out.println("Who do you think made this tweet?");
                    System.out.println("Type: <A> for <Elon Musk>; <B> for <Kanye West>");
                    String choiceMusk = scnr.nextLine();

                    // check if user entered the correct answer
                    if (choiceMusk.equals("A") || choiceMusk.equals("a")) {
                        System.out.println("You are right! +1 pt");
                        totalpt++;
                    } else if (choiceMusk.equals("B") || choiceMusk.equals("b")) {
                        System.out.println("Sorry, it's from Elon Musk. +0 pt");
                    } else {
                        System.out.println("Please enter the correct character. +0pt");
                    }

                }
                // when the tweet is by Kanye West
                else {
                    // randomly choose a tweet by Kanye West
                    innerbuffer = (int) (Math.random() * tweets2.size());

                    // check if the tweet does not contain references to other users or URLs
                    while (tweets2.get(innerbuffer).getText().indexOf('@') != -1
                            || tweets2.get(innerbuffer).getText().toLowerCase().contains("https")) {
                        innerbuffer = (int) (Math.random() * tweets2.size());
                    }

                    // simple formatting of the tweet displayed
                    for (int i = 0; i < tweets2.get(innerbuffer).getText().length(); ++i) {
                        System.out.print("*");
                    }
                    System.out.println();
                    System.out.println(tweets2.get(innerbuffer).getText());
                    for (int i = 0; i < tweets2.get(innerbuffer).getText().length(); ++i) {
                        System.out.print("*");
                    }
                    System.out.println();

                    // ask question
                    System.out.println("Who do you think made this tweet?");
                    System.out.println("Type: <A> for <Elon Musk>; <B> for <Kanye West>");
                    String choiceWest = scnr.nextLine();

                    // check if user entered the correct answer
                    if (choiceWest.equals("B") || choiceWest.equals("b")) {
                        System.out.println("You are right! +1 pt");
                        totalpt++;
                    } else if (choiceWest.equals("A") || choiceWest.equals("a")) {
                        System.out.println("Sorry, it's from Kanye West. +0 pt");
                    } else {
                        System.out.println("Please enter the correct character. +0pt");
                    }
                }


                // add to number of total games played in this round
                totalgames++;

                // check if user wants to stop this round
                System.out.println("You can type <stop> to end this round and check your scores.");
                System.out.println("Or press any other key to continue game.");
                System.out.println();
                System.out.println("------------------------------------------------------");
                end = scnr.nextLine();
            }


            // statistics and outcomes of the user's round
            if (totalgames == 0) {
                System.out.println("Thank you for playing.");
            } else if ((double) totalpt / totalgames > 0.8) {
                System.out.println("Excellent work! You've guessed " + totalpt + " out of " + totalgames + " games!");
            } else if ((double) totalpt / totalgames > 0.5) {
                System.out.println("Good job! You've guessed " + totalpt + " out of " + totalgames + " games!");
            } else {
                System.out.println("You've guessed " + totalpt + " out of " + totalgames + " games. You can do better than that :)");
            }

            // check if the user wants to go for a second round
            System.out.println("Would you like to tryAgain?");
            System.out.println("Type <Y> to continue. Press any other key to exit.");
            tryAgain = scnr.nextLine();
        }


        // ending
        System.out.println("Thank you for playing Guess Tweet! Have a nice day! :)");
    }
}
