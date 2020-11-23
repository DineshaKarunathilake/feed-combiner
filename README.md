##FeedCombiner

Combined a Rss feed and a Webcomic feed into one feed and returns

##Instructions

How to build and run
````
mvn clean install
docker build -t feed-combiner/feed-combiner .
docker run -p 8080:8080 feed-combiner/feed-combiner
````

Application will run at localhost:8080

````
localhost:8080
````
