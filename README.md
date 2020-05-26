# java-spark-webinar
## A collection of (no great) example of java dommy code to explore some Apache Spark Data preinciples


Spark allows programmers to develop complex, multi-step data pipelines using directed acyclic graph (DAG) pattern. It  supports in-memory data sharing across DAGs, so that different jobs can work with the same data.
Spark (can) runs on top of existing Hadoop Distributed File System (HDFS) infrastructure to provide enhanced and additional functionality. 

This example are designed to run locally, but if you remove in any main class the "local[]" option and provide the right way to read input you can use its on clusters.

## You can use a simple docker compose container to simply try this code in cluster mode

Follow this link to find a simple docker container that build a cluster with 1 master node and 2 worker.


[a link](https://github.com/gaetanofabiano/dockerSparkCluster)

## Install

check out the project and run

mvc clean install

# Apache Spark Version

3.0.0-preview

# FOR WINDOWS USERS ONLY
If you run this code on windows you could have this "harmless" Error
Spark Error – java.io.IOException: Could not locate executable null\bin\winutils.exe in the Hadoop binaries


The following error is due to missing winutils binary in the classpath while running Spark application. Winutils is a part of Hadoop ecosystem and is not included in Spark. The actual functionality of your  application may run correctly even after the exception is thrown. But it is better to have it in place to avoid unnecessary problems. In order to avoid error, download winutils.exe binary and add the same to the classpath.

To avoid this error copy the "winutils" folder in C:\ (or everywhere you want)
i.e. C:\winutils
ensure that you have the winutils.exe in the folder C:\winutils\bin folder 

Set environment variable HADOOP_HOME to C:\winutils

Restart the ide and everything should be ok.

If is not ok, dont' worry, If you are running on windows machine it means that is local mode, and in local mode you dont' care about this error.

