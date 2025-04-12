FROM eclipse-temurin:21-jre-jammy as builder
WORKDIR /app
COPY target/expense-tracking-api.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

# Create a non-root user
RUN useradd -r -u 1001 -g root appuser
USER appuser

EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
