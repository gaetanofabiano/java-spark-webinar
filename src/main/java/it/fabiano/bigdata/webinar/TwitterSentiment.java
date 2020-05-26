package it.fabiano.bigdata.webinar;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;

import it.fabiano.bigdata.webinar.utils.SentimentAnalyzer;
import it.fabiano.bigdata.webinar.utils.TwitterCredentials;
import scala.Tuple2;
import twitter4j.Status;

/**
 * Java-Spark-Webinar
 *
 * @author  Gaetano Fabiano
 * @version 1.0.0
 * @since   2020-05-26 
 */
public class TwitterSentiment  {

	

	public static void main(String[] args) throws InterruptedException  {

		

		
		String[] filters = {"trump"};



		System.setProperty("twitter4j.oauth.consumerKey", TwitterCredentials.consumerKey);
		System.setProperty("twitter4j.oauth.consumerSecret", TwitterCredentials.consumerSecret);
		System.setProperty("twitter4j.oauth.accessToken", TwitterCredentials.accessToken);
		System.setProperty("twitter4j.oauth.accessTokenSecret", TwitterCredentials.accessTokenSecret);

		SparkConf sparkConf = new SparkConf().setAppName("JavaTwitterHashTagJoinSentiments").setMaster("local[*]");
		//SparkConf sparkConf = new SparkConf().setAppName("JavaTwitterHashTagJoinSentiments");
		
		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, new Duration(2000));

		JavaReceiverInputDStream<Status> stream = TwitterUtils.createStream(jssc, filters);
		
		
		JavaDStream<String> tweets = stream.map(status -> status.getText());
	
		
		
//		JavaPairDStream<String, String> tweetWithScoreDStream =
//				tweets.mapToPair(tweetText -> 
//				new Tuple2<>(
//						"TWEET: "+tweetText, 
//						"SENTIMENT: "+Double.valueOf(SentimentAnalyzer.calculateWeightedSentimentScore(tweetText))));

		
		JavaPairDStream<String, String> tweetWithSentiment =
				tweets.mapToPair(tweetText -> 
				new Tuple2<>(
						"TWEET: "+tweetText, 
						"SENTIMENT: "+SentimentAnalyzer.getSimplifiedSentiment(tweetText)));
		
		//tweetWithScoreDStream.print();

		tweetWithSentiment.print();
		jssc.start();
		jssc.awaitTermination();
		
	}

	

}
