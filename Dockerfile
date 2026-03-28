FROM maven:latest

LABEL authors="riku"

WORKDIR /app

COPY . .

RUN mvn -f pom.xml clean package -DskipTests

CMD ["java","-jar","./target/otp2_inclass_week2-1.0-SNAPSHOT.jar"]