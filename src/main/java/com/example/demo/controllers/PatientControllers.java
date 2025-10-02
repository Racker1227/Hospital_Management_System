package com.example.demo.controllers;


import com.example.demo.models.Patient;
import com.example.demo.service.PatientService;

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

@RestController
@RequestMapping("/api/v1/patients")
public class PatientControllers 
{

	@Autowired
	private PatientService patientService;
	
	@GetMapping
	public Page<Patient>getAllPatients(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size)
	{
		System.out.println("Fetching the patients");
		return patientService.getAllPatients(page, size);
		// Implement service call to fetch all Patients
	}
	
	@PostMapping
	public Patient createPatient(@RequestBody Patient patient)
	{
		System.out.println("Creating patient");
		return patientService.createPatient(patient);
		// Implement service call to create patient
	}
	
	@GetMapping("/{id}")
	public Patient getPatientById(@PathVariable Long id)
	{
		System.out.println("Fetching id by ID: " + id);
		return patientService.getPatientById(id);
		// Implement service call to fetch patient by ID
	}
	
	@DeleteMapping("/{id}")
	public void deletePatient(@PathVariable Long id)
	{
		System.out.println("Deleting patient with ID: " + id);
		patientService.deletePatient(id);
		// Implement service call to delete Patient
	}
	

	@PutMapping("/{id}")
	public void updatePatient(@PathVariable Long id, @RequestBody Patient patient)
	{
		System.out.println("Updating patient with ID: " + id);
		patientService.updatePatient(id, patient);
		// Implement service call to update Patient
	}
	
	
}
