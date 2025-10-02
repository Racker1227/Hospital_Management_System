package com.example.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.models.Patient;
import com.example.demo.repositories.PatientRespository;


@Service
public class PatientService 
{
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

	@Autowired
	private PatientRespository patientRespository;
	
	public Page<Patient>getAllPatients(int page, int size)
	{
		//interact with repository layer
		try 
		{
			PageRequest pageable = PageRequest.of(page, size);
			return patientRespository.findAll(pageable);
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while fetching all patients: {}", e.getMessage(), e);
			return null;
		}
	}
	
	public Patient getPatientById(Long id)
	{
		try 
		{
			
			Optional<Patient> patient = patientRespository.findById(id);
			return patient.orElse(null);

		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while fetching patient with Id {}: {}", id, e.getMessage(), e);
			return null;
		}
	}
	
	public Patient createPatient(Patient patient)
	{
		try 
		{
			
			patientRespository.save(patient);
			return patient;
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while creating patient : {}", e.getMessage(), e);
			return null;
		}
	}
	
	
	public void deletePatient(Long id)
	{
		try 
		{
			patientRespository.deleteById(id);
			
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while deleting patient with Id {}: {}", id, e.getMessage(), e);
			
		}
	}
	
	public Patient updatePatient(Long id, Patient updatedPatient)
	{
		try 
		{
			Optional<Patient> existingPatient = patientRespository.findById(id); 
			if(existingPatient.isPresent())
			{
				Patient p = existingPatient.get();
				p.setName(updatedPatient.getName());
				p.setAge(updatedPatient.getAge());
				p.setGender(updatedPatient.getGender());
				patientRespository.save(p);
				
				return updatedPatient;
			}
			else 
			{
				logger.error("Patient with ID {} not found", id);
				return null;
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while updating patient with Id {}: {}", id, e.getMessage(), e);
			return null;
		}
		
	}
	
	
	
}
