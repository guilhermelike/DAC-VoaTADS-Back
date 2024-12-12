# Etapa 1: Construir a aplicação usando uma imagem Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Define o diretório de trabalho dentro do container para o build
WORKDIR /app

# Copia o arquivo de configuração do Maven (opcional)
COPY pom.xml .

# Baixa as dependências do Maven para o cache
RUN mvn dependency:go-offline

# Copia o restante do código da aplicação para o contêiner
COPY src ./src

# Compila a aplicação e cria o jar
RUN mvn package -DskipTests

# Etapa 2: Criar a imagem final com o runtime Java
FROM eclipse-temurin:17-jdk-alpine

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Define a porta que o serviço irá expor
EXPOSE 5000

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]