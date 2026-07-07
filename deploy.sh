
# Script de automatización para desplegar microservicios en Docker Swar
echo "Inicialización despliegue de Sanos y Salvos en Docker Swarm"

echo "Verificación estado de Docker Swarm."
docker swarm init 2>/dev/null || echo "Docker Swarm ya se encuentra inicializado en este nodo."

echo "Desplegando el stack de microservicios..."
docker stack deploy -c docker-compose.yml sanosysalvos --with-registry-auth

echo "Despliegue exitoso"
echo ""
echo "Para verificar el estado de los contenedores, ejecuta:"
echo "   docker service ls"