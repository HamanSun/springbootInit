FROM java:8
MAINTAINER JunjieSun 13662005880@163.com
VOLUME /tmp
ADD sc-docker-1.0.jar app.jar
#RUN bash -c 'touch /app.jar'
#RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \ && echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8761
