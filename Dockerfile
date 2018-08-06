FROM ubuntu:16.04
MAINTAINER Kavisha Agarwal, agarwalkavisha18@gmail.com

# Install all packages
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get clean

EXPOSE 8080

# Set correct environment variables - Java 8
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle/bin/java
ENV APP_JAR key-value-pairs.jar
ENV CONFIG_FILE conf/config.yml
RUN mkdir -p /conf
COPY conf/config.yml /conf/
COPY target/key-value-pairs-*.jar key-value-pairs.jar
COPY run.sh /
RUN chmod +x /run.sh
WORKDIR /
CMD ["/bin/bash", "run.sh"]
