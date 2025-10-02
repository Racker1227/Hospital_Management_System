package com.example.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.models.Bill;
import com.example.demo.repositories.BillRepository;


@Service
public class BillService {

	private static final Logger logger = LoggerFactory.getLogger(BillService.class);

	@Autowired
	private BillRepository billRepository;
	
	public Page<Bill>getAllBills(int page, int size)
	{
		//interact with repository layer
		try 
		{
			PageRequest pageable = PageRequest.of(page, size);
			return billRepository.findAll(pageable);
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while fetching all bills: {}", e.getMessage(), e);
			return null;
		}
	}
	
	public Bill getBillById(Long id)
	{
		try 
		{
			Optional<Bill> bill = billRepository.findById(id);
			return bill.orElse(null);
			
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while fetching Bill with Id {}: {}", id, e.getMessage(), e);
			return null;
		}
	}
	
	public Bill createBill(Bill bill)
	{
		try 
		{
			billRepository.save(bill);
			return bill;
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while creating bill : {}", e.getMessage(), e);
			return null;
		}
	}
	
	
	public void deleteBill(Long id)
	{
		try 
		{
			billRepository.deleteById(id);
			
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while deleting bill with Id {}: {}", id, e.getMessage(), e);

		}
	}
	
	public Bill updateBill(Long id, Bill updatedBill)
	{
		try 
		{
			Optional<Bill> existingBill = billRepository.findById(id);
			if(existingBill.isPresent())
			{
				Bill b = existingBill.get();
				b.setPatientId(updatedBill.getPatientId());
				b.setAmount(updatedBill.getAmount());
				b.setStatus(updatedBill.getStatus());
				
				return updatedBill;
						
			}
			else 
			{
				logger.error("Bill with ID {} not found", id);
				return null;
			}
			
		} 
		catch (Exception e) 
		{
			System.out.println("Error Message: " + e.getMessage());
			logger.error("An error occured while updating bill with Id {}: {}", id, e.getMessage(), e);
			return null;
		}
	}
	
	
	
	
	
}
