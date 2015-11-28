FROM java:7
MAINTAINER Dipesh Rane
EXPOSE 8888
ENV JAVA_OPTS -Xmx1024m -XX:MaxPermSize=128M -Djava.security.egd=file:/dev/./urandom
VOLUME /tmp
ADD billfoldwebservices-1.0.0.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]