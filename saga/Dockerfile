# Use a imagem base do Maven para construir o projeto
FROM maven:3.8.4-openjdk-17 AS build

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo pom.xml e as dependências do projeto
COPY pom.xml .
COPY src ./src

# Execute o comando de build do Maven para criar o artefato
RUN mvn clean package -DskipTests

# Use uma imagem base do OpenJDK 17 para rodar a aplicação
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o artefato gerado pelo Maven para o contêiner
COPY --from=build /app/target/saga-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que a aplicação irá rodar
EXPOSE 5010

# Defina o comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]