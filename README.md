# Servicio de Reserva
Microservicio programado en Java que expone una api de reservas de hoteles y vuelos en el puerto 8082, es dependiente de los microservicios de hoteles y vuelos
## Descripcion
El microservicio de reservas expone una api con las siguientes operaciones

GET "/api/reservas" Devuelve todas las reservas existentes en la base de datos

GET "api/reservas/id/{id}" Devuelve la reserva que coincida con el identificador {id} o revuelve un error codigo 404


