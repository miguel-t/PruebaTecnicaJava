FROM openjdk:11
COPY "./target/java-sat-recruitment-1.0.0-SNAPSHOT.jar" "java-recruitment.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","java-recruitment.jar"]