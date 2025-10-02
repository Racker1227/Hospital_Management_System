package com.example.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.models.Appointment;
import com.example.demo.repositories.AppointmentRepository;


@Service
public class AppointmentService {
	
	private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	
	public Page<Appointment>getAllAppointments(int page, int size)
	{
		//interact with repository layer
		try 
		{
			PageRequest pageable = PageRequest.of(page, size);
			return appointmentRepository.findAll(pageable);
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while fetching all Appointments: {}", e.getMessage(), e);
			
			return null;
		}
	}
	
	public Appointment getAppointmentById(Long id)
	{
		try 
		{
			Optional<Appointment> appointment=  appointmentRepository.findById(id);
			return appointment.orElse(null);
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while fetching apointment with Id {}: {}", id, e.getMessage(), e);
			return null;
		}
	}
	
	public Appointment createAppointment(Appointment appointment)
	{
		try 
		{
			appointmentRepository.save(appointment);
			return appointment;
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while creating appointment : {}", e.getMessage(), e);
			return null;
		}
	}
	
	
	public void deleteAppointment(Long id)
	{
		try 
		{
			appointmentRepository.deleteById(id);
			
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while deleting appointment with Id {}: {}", id, e.getMessage(), e);

		}
	}
	
	public Appointment updateAppointment(Long id, Appointment updatedAppointment)
	{
		try 
		{
			Optional<Appointment> existingAppointment = appointmentRepository.findById(id);
			if(existingAppointment.isPresent())
			{
				Appointment a = existingAppointment.get();
				a.setPatientId(updatedAppointment.getPatientId());
				a.setDoctorId(updatedAppointment.getDoctorId());
				a.setDate(updatedAppointment.getDate());
				appointmentRepository.save(a);
				
				return updatedAppointment;
				
			}
			else 
			{
				logger.error("Appointment with ID {} not found", id);
				return null;
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while updating appointment with Id {}: {}", id, e.getMessage(), e);
			return null;
		}
	}
	
	
	
	
}
