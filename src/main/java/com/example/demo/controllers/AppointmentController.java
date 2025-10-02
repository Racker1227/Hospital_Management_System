package com.example.demo.controllers;

import java.util.HashMap;
import java.util.Map;
import com.example.demo.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Appointment;
import com.example.demo.service.AppointmentService;


@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final WebhookService webhookService;

	@Autowired
	private AppointmentService appointmentService;

    AppointmentController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }
	
	@GetMapping
	public Page<Appointment>getAllAppointments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size)
	{
		System.out.println("Fetching the appointment");
		return appointmentService.getAllAppointments(page,size);
		// Implement service call to fetch all appointments
	}
	
	@PostMapping
	public Appointment createAppointment(@RequestBody Appointment appointmentRequest)
	{
		System.out.println("Creating appointment");
		
		Appointment appointment = appointmentService.createAppointment(appointmentRequest);
		
		// Preapare the webhook pauload
		Map<String, Object> payload = new HashMap<>();
		payload.put("appointmentId", appointment.getId());
		payload.put("patientid", appointment.getPatientId());
		payload.put("doctorId", appointment.getDoctorId());
		payload.put("appointmentDate", appointment.getDate());
		
		// Send the webhook
		String webhookUrl="http://localhost:8081/webhook"; //Replace witht yoyr actuall webhook
		webhookService.sendWebhook(webhookUrl, payload);
		
		
		return appointment;
		//return appointmentService.createAppointment(appointment);
		// Implement service call to create appointment
	}
	
	@GetMapping("/{id}")
	public Appointment getAppointmentById(@PathVariable Long id)
	{
		System.out.println("Fetching id by ID: " + id);
		return appointmentService.getAppointmentById(id);
		// Implement service call to fetch appointment by ID
	}
	
	@DeleteMapping("/{id}")
	public void deleteAppointment(@PathVariable Long id, @RequestBody Appointment appointment)
	{
		System.out.println("Deleting appointment with Id: " + id);
		appointmentService.deleteAppointment(id);
		// Implement service call to delete appointment
	}
	

	@PutMapping("/{id}")
	public void updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment)
	{
		System.out.println("Updating appointment with ID: " + id);
		appointmentService.updateAppointment(id, appointment);
		// Implement service call to update appointment
	}
	
	
	
}
