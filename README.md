Lista de servicios:

1. ms-gateway (Puerto 8000)
2. ms-auth (Puerto 8080)
3. ms-identity (Puerto 8081)
4. ms-geo (Puerto 8082)
5. ms-mascotas (Puerto 8083)
6. postgres-db (Puerto 5432)

Desarrollo en local:

Prerrequisitos
1. Java Development Kit (JDK 17+)
2. Docker y Docker Desktop instalados y corriendo.
3. Maven (o usar el wrapper incluido `mvnw`).

Pasos
1. Clonar repositorio : https://github.com/JonasMateosValenzuelaLopez/Proyecto-semestral-java.git
    cd Proyecto-semestral-java
2. Compilar en cada microservicio : mvn clean package -DskipTests
3. Construir imagenes de docker localmente: docker-compose build
4. Levantar ecosistema: docker-compose up -d


Despliegue en AWS

1. Despliegue rápido y permisos de ejecución: chmod +x deploy.sh
2. Ejecutar despliegue: ./deploy.sh

Escalabilidad

1. Para añadir un nodo worker: 
    En el Manager ejecuta docker swarm join-token worker y pega el comando resultante en el nuevo servidor.
2. Para añadir un nodo manager: 
    En el Manager ejecuta docker swarm join-token manager y pega el comando resultante en el nuevo servidor.


Comandos útiles

1. Revisar estado de la orquesta: docker service ls
2. Ver registros de un servicio: docker service logs sanosysalvos_ms-mascotas
3. Escalar un microservicio: docker service scale sanosysalvos_ms-mascotas=3
4. Apagar el ecosistema: docker stack rm sanosysalvos

