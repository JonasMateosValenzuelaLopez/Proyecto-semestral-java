Archivos de Configuración

1. index.js
2. Runtime requerido: `Node.js 18.x` o superior

Instrucciones de Despliegue en AWS (Vía AWS CLI)

1. Compress-Archive -Path index.js -DestinationPath function.zip

-- Reemplazar "TU_ACCOUNT_ID" con ID de cuent AWS --
2. 
aws lambda create-function \
  --function-name SanosYSalvos_Notificador \
  --runtime nodejs18.x \
  --zip-file fileb://function.zip \
  --handler index.handler \
  --role arn:aws:iam::TU_ACCOUNT_ID:role/LabRole 


Ejemplo de prueba

aws lambda invoke \
  --function-name SanosYSalvos_Notificador \
  --payload '{"nombreMascota": "Bobby", "especie": "Perro", "ubicacion": "Plaza Ñuñoa"}' \
  --cli-binary-format raw-in-base64-out \
  response.json