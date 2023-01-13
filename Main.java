import java.lang.module.Configuration;
import java.util.UUID;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
public class Main{

    public static void main(String []args) throws TwitterException, InterruptedException{
        String user="DennysDiner";
        String message="First";
        String consumerKey="";
        String consumerSecret="";
        String accessToken="";
        String accessTokenSecret="";
        ConfigurationBuilder cb= new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf=new TwitterFactory(cb.build());
        Twitter twitter=tf.getInstance();
        long initalStatusiD=twitter.showUser(user).getStatus().getId();
        int x = 0;
        while(x==0){
            long inReplyToStatusId;
            try{
                inReplyToStatusId=twitter.showUser(user).getStatus().getId();
            }catch(Exception fs){
                inReplyToStatusId=initalStatusiD;
            }
            if(inReplyToStatusId!=initalStatusiD){
                twitter.updateStatus(new StatusUpdate("@" + user + " "+message).inReplyToStatusId(inReplyToStatusId));
                message = message+"!";
                initalStatusiD = inReplyToStatusId;
                System.out.println("Success!");
                x++;
            }
            if(x == 1){
                break;
            }
            Thread.sleep(1000);
        }
    }
}
