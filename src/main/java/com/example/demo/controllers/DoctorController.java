package com.example.demo.controllers;

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

import com.example.demo.models.Doctor;
import com.example.demo.service.DoctorService;



@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;

	@GetMapping
	public Page<Doctor>getAllDoctors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size)
	{
		System.out.println("Fetching the doctor");
		return doctorService.getAllDoctors(page,size);
		// Implement service call to fetch all Doctors
	}
	
	@PostMapping
	public Doctor createDoctor(@RequestBody Doctor doctor)
	{
		System.out.println("Creating doctor");
		return doctorService.createDoctor(doctor);
		// Implement service call to create Doctor
	}
	
	@GetMapping("/{id}")
	public Doctor getDoctorById(@PathVariable Long id)
	{
		System.out.println("Fetching id by ID: " + id);
		return doctorService.getDoctorById(id);
		// Implement service call to fetch Doctor by ID
	}
	
	@DeleteMapping("/{id}")
	public void deleteDoctor(@PathVariable Long id, @RequestBody Doctor doctor)
	{
		System.out.println("Deleting doctor with Id: " + id);
		doctorService.deleteDoctor(id);
		// Implement service call to delete doctor
	}
	

	@PutMapping("/{id}")
	public void updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor)
	{
		System.out.println("Updating doctor with ID: " + id);
		doctorService.updateDoctor(id, doctor);
		// Implement service call to update doctor
	}
	
	
}
