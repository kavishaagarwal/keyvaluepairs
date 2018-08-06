# Key Value Pairs
This file contains setup instructions for key-value-pairs

# Setup steps:

Navigate to the project location
```sh
$ cd <path to key-value-pairs project>
```

Build and compile
```sh
$ mvn clean install
```

Running key-value-pairs executable
```sh
$ java -jar target/key-value-pairs-1.0.0.jar server conf/config.yml
```

# Docker setup instructions

Building the docker image

```sh
$ docker build -t keyvaluepairs_image .
```

Running the docker image
We have exposed port 8080 for docker container and forwarded it to port 6379 on our local machine
```sh
$ docker run -p 6379:8080 --name keyvaluepairs_instance -t keyvaluepairs_image
```

# Postman API collection link
https://www.getpostman.com/collections/c6c5cbaa3baf81ff6782
"Upload CSV" API uploads data into the service. The data is taken from the file "<path-to-project>/keyvaluepairs/src/main/resources/corpus.csv".
"Get Key Value Pair" API fetches the key value pair corresponding to the given value
