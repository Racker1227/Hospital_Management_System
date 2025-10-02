package com.example.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.models.Doctor;
import com.example.demo.repositories.DoctorRepository;


@Service
public class DoctorService {
	
	private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

	@Autowired
	private DoctorRepository doctorRepository;
	
	public Page<Doctor>getAllDoctors(int page, int size)
	{
		//interact with repository layer
		try 
		{
			PageRequest pageable = PageRequest.of(page, size);
			return doctorRepository.findAll(pageable);
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while fetching all doctors: {}", e.getMessage(), e);
			return null;
		}
	}
	
	public Doctor getDoctorById(Long id)
	{
		try 
		{
			
			Optional<Doctor> doctor = doctorRepository.findById(id);
			return doctor.orElse(null);
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while fetching doctor with Id {}: {}", id, e.getMessage(), e);
			return null;
		}
	}
	
	public Doctor createDoctor(Doctor doctor)
	{
		try 
		{
			
			doctorRepository.save(doctor);
			return doctor;
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while creating doctor : {}", e.getMessage(), e);
			return null;
		}
	}
	
	
	public void deleteDoctor(Long id)
	{
		try 
		{
			doctorRepository.deleteById(id);
			
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while deleting doctor with Id {}: {}", id, e.getMessage(), e);

		}
	}
	
	public Doctor updateDoctor(Long id, Doctor updatedDoctor)
	{
		try 
		{
			Optional<Doctor> existingDoctor = doctorRepository.findById(id);
			if(existingDoctor.isPresent())
			{
				Doctor d = existingDoctor.get();
				d.setName(updatedDoctor.getName());
				d.setSpeciality(updatedDoctor.getSpeciality());
				doctorRepository.save(d);
				
				return updatedDoctor;
			}
			else 
			{
				logger.error("Doctor with ID {} not found");
				return null;
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while updating doctor with Id {}: {}", id, e.getMessage(), e);
			return null;
		}
	}
	
	

}
