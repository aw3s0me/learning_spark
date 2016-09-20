import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Created by alex on 20.09.16.
 */
public class WordCount {
    public static void main(String[] args) {
        // Create a Java Spark Context
        SparkConf conf = new SparkConf().setMaster("local").setAppName("wordcount");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Parallelize with 2 partitions
        JavaRDD<String> input = sc.parallelize(Arrays.asList("Spark RDD Example", "Sample Example"), 2);

        // Return array list

        // Split into words
        JavaRDD<String> words = input.flatMap(e -> Arrays.asList(e.split(" ")).iterator());

        // Transform into pairs and count
        JavaPairRDD<String, Integer> counts = words.mapToPair(w -> new Tuple2<>(w, 1))
                .reduceByKey((x, y) -> x + y);

        // Save word count back out to text file
        counts.saveAsTextFile("counts");
    }
}
