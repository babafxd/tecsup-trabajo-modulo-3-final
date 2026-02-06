# MARCO SAAVEDRA: TRABAJO FINAL

## **Indicaciones**
**Cada proyecto tiene su carpeta database para ejecutar su script:


**Se adjunta archivo yml para los contenedores,base de datos y Kafka:
docker-compose.yml

**Se adjunta archivo json para las pruebas:
MSAAVEDRA-POSTMAN.postman_collection.json


El proyecto consta de 4 microservicios y 1 worker para notificaciones

Microservicios:
- Courses 		: http://localhost:8082/api/courses
- Users			: http://localhost:8083/api/users
- Enrollment	: http://localhost:8084/api/enrollments
- Payment		: http://localhost:8085/api/payments

Worker:
- payment-service



**Se realizaron las pruebas con todos los CASOS DE USO:

Caso 1: Publicar curso (OK) 

	Admin crea curso
	Admin publica curso (POST /courses/{id}/publish)
	course-service emite evento opcional CoursePublishedEvent (OK)
	todos los endpoints (OK)


* Se completo service de users.

Caso 2: Solicitud de matrícula (OK)
	Cliente llama POST /enrollments
	enrollment-service valida usuario y curso vía RestTemplate
	Matrícula queda en estado PENDING_PAYMENT
	enrollment-service publica EnrollmentCreatedEvent
	notification-service notifica al estudiante
	todos los endpoints (OK)
	
Caso 3: Pago
	Cliente llama POST /payments (OK)

	payment-service registra pago y publica:
		PaymentApprovedEvent (OK) >= 100
		PaymentRejectedEvent (OK) <= 1
	
	enrollment-service consume evento y actualiza matrícula: (OK)
		CONFIRMED o CANCELLED
		
	enrollment-service publica EnrollmentUpdatedEvent  (OK)
	notification-service notifica el resultado final   (OK)
	
	ENDPOINTS:
	payment-service	/payments	POST	Registrar pago   (OK)
	payment-service	/payments/{id}	GET	Consultar pago   (OK)


publicar curso → matrícula → pago → confirmación → notificación

---


