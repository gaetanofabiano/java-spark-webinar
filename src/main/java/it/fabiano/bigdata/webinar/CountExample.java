package it.fabiano.bigdata.webinar;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
/**
 * Java-Spark-Webinar
 *
 * @author  Gaetano Fabiano
 * @version 1.0.0
 * @since   2020-05-26 
 */
public class CountExample {

    public static void main(String[] args) throws Exception {
    	
        Logger.getLogger("org").setLevel(Level.INFO);
        
        SparkConf conf = new SparkConf().setAppName("count").setMaster("local[1]");
       
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> inputWords = Arrays.asList("spark", "hadoop", "spark", "hive", "spark", "cassandra", "hadoop", "kakfa");
        
        JavaRDD<String> wordRdd = sc.parallelize(inputWords);

        Map<String, Long> wordCountByValue = wordRdd.countByValue();
        

        for (Map.Entry<String, Long> entry : wordCountByValue.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
       
        sc.close();
    }
}
