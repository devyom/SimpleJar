FROM java:8

ADD target/SimpleJar-1.0-SNAPSHOT.jar /root/app.jar
ADD config.yml /root/config.yml
ADD start.sh /root/start.sh
RUN chmod a+x /root/start.sh

CMD ["/root/start.sh"]
