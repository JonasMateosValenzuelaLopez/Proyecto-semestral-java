package com.sanosysalvos.ms_mascotas.integracion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanosysalvos.ms_mascotas.models.Mascota;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import java.util.HashMap;
import java.util.Map;

@Service
public class LambdaNotificadorService {

    private static final Logger logger = LoggerFactory.getLogger(LambdaNotificadorService.class);
    private static final String NOMBRE_FUNCION = "SanosYSalvos_Notificador";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void notificarMascotaPerdida(Mascota mascota) {
        try (LambdaClient lambdaClient = LambdaClient.builder()
                .region(Region.US_EAST_1)
                .build()) {

            Map<String, String> payload = new HashMap<>();
            payload.put("nombreMascota", mascota.getNombre() != null ? mascota.getNombre() : "Desconocido");
            payload.put("especie", mascota.getEspecie() != null ? mascota.getEspecie() : "Animal");
            payload.put("ubicacion", mascota.getUbicacionId() != null
                    ? "Ubicacion registrada ID " + mascota.getUbicacionId()
                    : "Ubicacion no especificada");

            String jsonPayload = objectMapper.writeValueAsString(payload);

            InvokeRequest request = InvokeRequest.builder()
                    .functionName(NOMBRE_FUNCION)
                    .payload(SdkBytes.fromUtf8String(jsonPayload))
                    .build();

            InvokeResponse response = lambdaClient.invoke(request);
            String respuestaTexto = response.payload().asUtf8String();

            if (response.functionError() != null) {
                logger.warn("La Lambda {} respondio con error: {}", NOMBRE_FUNCION, respuestaTexto);
            } else {
                logger.info("Notificacion enviada para mascota '{}': {}", mascota.getNombre(), respuestaTexto);
            }

        } catch (Exception e) {
            logger.warn("No se pudo invocar la Lambda notificadora (el registro de la mascota continua igual): {}",
                    e.getMessage());
        }
    }
}