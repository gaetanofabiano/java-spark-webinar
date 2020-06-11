package it.fabiano.bigdata.webinar;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
/**
 * Java-Spark-Webinar
 *
 * @author  Gaetano Fabiano
 * @version 1.0.0
 * @since   2020-05-26 
 */
public class ParallelizeExample {
	public static void main(String[] args) {

		Logger.getLogger("org").setLevel(Level.ERROR);
		SparkConf conf = new SparkConf().setAppName("RDD-creator").setMaster("local[2]");
		JavaSparkContext sc = new JavaSparkContext(conf);




		List<Integer> listInt = new ArrayList<Integer>();
		listInt.add(1);
		listInt.add(2);
		listInt.add(3);
		listInt.add(4);
		listInt.add(5);
		listInt.add(6);

		System.out.println("this is the list");
		for (Integer integer : listInt) {
			System.out.println(integer);
		}

		System.out.println("this is the RDD");
		JavaRDD<Integer> intRDD = sc.parallelize(listInt);



		intRDD.foreach(f-> System.out.println(f));

		sc.close();
	}
}
