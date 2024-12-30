***Steps to Dockerize a Spring Boot Application***


**Step 1: Create a Spring Boot Application**

Set up a basic Spring Boot project using Spring Initializr or your preferred IDE.

Make sure your application runs successfully locally.
**Step 2: Create the Dockerfile**

Add a Dockerfile in the root directory of the project. This file defines the instructions for building the Docker image.
Example Dockerfile:

FROM eclipse-temurin:17
LABEL maintainer="akashhm@gmail.com"
WORKDIR /app
COPY target/Rest-Demo.jar /app/Rest-Demo-Docker.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","Rest-Demo-Docker.jar"]

FROM eclipse-temurin:17: Specifies the base image (Java 17) for your container.

LABEL maintainer="akashhm@gmail.com": Adds metadata for the container, including the maintainer's contact.

WORKDIR /app: Sets the working directory inside the container to /app.

COPY target/Rest-Demo.jar /app/Rest-Demo-Docker.jar: Copies the JAR file into the container.

EXPOSE 8088: Exposes port 8088 for accessing the application.

ENTRYPOINT ["java", "-jar", "Rest-Demo-Docker.jar"]: Defines the command to run the application when the container starts.

**Step 3: Build the Application JAR**

Use Maven or Gradle to build the JAR file for your Spring Boot application.

Example Maven command:
mvn clean package

This creates the Rest-Demo.jar file in the target/ directory.

**Step 4: Build the Docker Image**

After building the JAR file, use the Docker CLI to create an image.

Run the following command from the root directory of your project (where the Dockerfile is located):

docker build -t rest-demo .

**Step 5: Run the Docker Container**

Once the image is built, run the Spring Boot application in a Docker container:

docker run -p 8088:8088 rest-demo

This command maps port 8088 on your local machine to port 8088 inside the container, so you can access your Spring Boot application at http://localhost:8088.

**Step 6: Create the Docker Compose File**

Create a docker-compose.yml file that defines both the Spring Boot application and the PostgreSQL database as services. This file will manage the containers and their interactions.
Example docker-compose.yml:

yaml
Copy code
version: "3.9"
services:
  postgres:
    container_name: new-postgres
    image: postgres
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: password
      POSTGRES_DB: postgres
    ports:
      - "5432:5432"
    volumes:
      - postgres:/var/lib/postgresql/data
    networks:
      - spring-boot-network

  spring-boot-application:
    container_name: spring-demo
    image: spring/demo
    ports:
      - "8090:8088"
    networks:
      - spring-boot-network
    depends_on:
      - postgres

volumes:
  postgres:

networks:
  spring-boot-network:
    driver: bridge

    
**Explanation of the docker-compose.yml File:**
services:: Defines the services (containers) that will be run in the Docker Compose setup.

**postgres::**

This service runs a PostgreSQL container.

image: postgres: Specifies the official PostgreSQL image from Docker Hub.

environment:: Sets up environment variables needed to initialize the PostgreSQL database (user, password, and database name).

ports:: Exposes port 5432 on the host machine to connect to PostgreSQL from outside the container.

volumes:: Mounts a named volume (postgres) to persist the PostgreSQL data in the /var/lib/postgresql/data directory inside the container. This ensures data is not lost when the container is stopped or recreated.

networks:: Connects this service to the spring-boot-network defined later, allowing the PostgreSQL service to communicate with the Spring Boot application container.

**spring-boot-application::**

This service runs your Spring Boot application in a separate container.

image: spring/demo: Specifies the Docker image that contains the Spring Boot application.

ports:: Maps port 8088 inside the container to port 8090 on the host machine, making the Spring Boot application accessible at http://localhost:8090.

depends_on:: Ensures that the spring-boot-application container will only start after the postgres container is running.

networks:: Connects this service to the same spring-boot-network network as the PostgreSQL container, enabling communication between the two services.

volumes::
postgres:: This section defines a named volume (postgres) to persist the data of the PostgreSQL database. Named volumes ensure data remains intact across container restarts.

networks::
spring-boot-network:: Defines a custom network for the containers to communicate with each other. The bridge driver is used to create a network that allows the containers to communicate in isolation from the host machine.


**Step 7: Build and Start the Containers**

From the root directory of your project (where the docker-compose.yml file is located), run the following command to build and start the containers:

docker-compose up --build

This command:
Builds the images (if necessary).
Starts the PostgreSQL and Spring Boot application containers.

Sets up the networking between the containers.


**Step 8: Access the Application**

Once the containers are running, you can access the Spring Boot application at http://localhost:8090 (or the port specified in your docker-compose.yml file).

The PostgreSQL database can be accessed through port 5432.

**Step 9: Stop the Containers**

To stop and remove the containers, run the following command:

docker-compose down

This command stops and removes the containers. However, the data inside the PostgreSQL database will persist due to the mounted volume, and the containers can be restarted without losing the data.

**Key Features**
Multi-container Setup: The docker-compose.yml file defines both the Spring Boot application and the PostgreSQL database as separate services, each running in its own container.

Persistent Data with Volumes: PostgreSQL data is stored in a Docker volume, ensuring that the data is preserved across container restarts.

Network Isolation: The containers are connected via a custom network, enabling them to communicate with each other but remain isolated from other containers and services.

Simplified Container Management: Docker Compose makes it easy to manage and orchestrate multiple containers as part of a single application.








