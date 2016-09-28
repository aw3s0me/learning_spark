#Running example
$ ./bin/spark-submit --class org.apache.spark.examples.SparkPi --master local --num-executors 3 --driver-memory 512m --executor-memory 512m --executor-cores 1 ./examples/jars/spark-examples*.jar 10
