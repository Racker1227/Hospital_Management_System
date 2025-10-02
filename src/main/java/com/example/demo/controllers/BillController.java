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

import com.example.demo.models.Bill;
import com.example.demo.service.BillService;



@RestController
@RequestMapping("/api/v1/bills")
public class BillController {
	
	@Autowired
	private BillService billService;

	@GetMapping
	public Page<Bill>getAllBills(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size)
	{
		System.out.println("Fetching the bill");
		return billService.getAllBills(page,size);
		// Implement service call to fetch all Bills
	}
	
	@PostMapping
	public Bill createBill(@RequestBody Bill bill)
	{
		System.out.println("Creating bill");
		return billService.createBill(bill);
		// Implement service call to create bill
	}
	
	@GetMapping("/{id}")
	public Bill getBillById(@PathVariable Long id)
	{
		System.out.println("Fetching id by ID: " + id);
		return billService.getBillById(id);
		// Implement service call to fetch bill by ID
	}
	
	@DeleteMapping("/{id}")
	public void deleteBill(@PathVariable Long id, @RequestBody Bill bill)
	{
		System.out.println("Deleting bill with ID: "+ id);
		billService.deleteBill(id);
		// Implement service call to delete bill
	}
	

	@PutMapping("/{id}")
	public void updateBill(@PathVariable Long id, @RequestBody Bill bill)
	{
		System.out.println("Updating bill with ID: " + id);
		billService.updateBill(id, bill);
		// Implement service call to update bill
	}
	
	
	
}
