// index.js - AWS Lambda para Sanos y Salvos
exports.handler = async (event) => {
    console.log("=== INICIANDO FUNCIÓN SERVERLESS: Notificador de Alertas ===");
    
    try {
        const mascota = event.nombreMascota || "Mascota Desconocida";
        const especie = event.especie || "Animal";
        const ubicacion = event.ubicacion || "Ubicación no especificada";
        
        console.log(`Alerta recibida: Se ha perdido un ${especie} llamado ${mascota} en ${ubicacion}.`);

        console.log("Simulando envío de correos a voluntarios en un radio de 5km...");
        console.log("¡Correos enviados con éxito!");

        const response = {
            statusCode: 200,
            body: JSON.stringify({
                mensaje: `Alerta procesada y notificaciones enviadas para ${mascota}.`,
                estado: "EXITO"
            }),
        };
        return response;
        
    } catch (error) {
        console.error("Error al procesar la alerta: ", error);
        return {
            statusCode: 500,
            body: JSON.stringify({ mensaje: "Error interno en el notificador." })
        };
    }
};