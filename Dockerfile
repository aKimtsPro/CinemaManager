#FROM tomcat:10-jdk17-corretto
#ADD /target/cinema-manager-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/cinema.war
#EXPOSE 8080
#CMD ["catalina.sh", "run"]


FROM postgres
ENV POSTGRES_PASSWORD="Test1234="
ENV POSTGRES_DB="personne_db"
