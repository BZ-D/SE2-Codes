FROM maven:3.6.1 AS compile_stage
ENV PROJECT_NAME courselearning
ENV WORK_PATH /opt/${PROJECT_NAME}
COPY settings.xml /root/.m2/settings.xml
COPY . ${WORK_PATH}
RUN cd ${WORK_PATH} && mvn clean install -DskipTests -DfinalName=${PROJECT_NAME}

FROM java:8-jre-alpine
ENV PROJECT_NAME courselearning
ENV WORK_PATH /opt/${PROJECT_NAME}
WORKDIR /app
COPY --from=compile_stage ${WORK_PATH}/target/${PROJECT_NAME}.jar .
CMD ["sh", "-c", "java -Xdebug -Xrunjdwp:transport=dt_socket,address=8888,server=y,suspend=n -jar -server /app/${PROJECT_NAME}.jar"]