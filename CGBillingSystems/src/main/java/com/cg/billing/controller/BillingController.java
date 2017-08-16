package com.cg.billing.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;

import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.exceptions.BillingServicesDownException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.services.IBillingServices;




@RestController
public class BillingController {
	
	
	@Autowired
	private IBillingServices services;
	
	@RequestMapping(value="/acceptCustomerDetail",method=RequestMethod.POST,consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String>acceptCustomerDetail(@ModelAttribute Customer customer) throws BillingServicesDownException{
		services.acceptCustomerDetails(customer);
		return new ResponseEntity<>("Customer details succesfully added",HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/deletecustomerDetails/{customerID}",method=RequestMethod.DELETE)
	public ResponseEntity<String>deleteCustomerDetails(@PathVariable("customerID") int customerID) throws CustomerDetailsNotFoundException, BillingServicesDownException{
        services.deleteCustomer(customerID);
		return new ResponseEntity<>("Customer details  successfully deleted",HttpStatus.OK);
	}
	
	@RequestMapping(value={"/allCustomerDetailsJSON"},produces=MediaType.APPLICATION_JSON_VALUE,
			headers ="Accept=application/json")
	public ResponseEntity<List<Customer>> getAllProductDetailsJSON() throws BillingServicesDownException, CustomerDetailsNotFoundException{
	   List<Customer>customer=services.getAllCustomerDetails();
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getcustomerDetails/{customerID}",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,
			headers="Accept=application/json")
	public ResponseEntity<Customer>getProductDetailsRequestParam(@PathVariable("customerID") int customerID) 
			throws CustomerDetailsNotFoundException, BillingServicesDownException{
		Customer customer =services.getCustomerDetails(customerID);
		if(customer==null)throw new CustomerDetailsNotFoundException("customer details with customer id"+customerID+" not found");
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	@RequestMapping(value="/openPostpaidAccount",method=RequestMethod.POST,consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String>openPostpaidAccount(@ModelAttribute Customer customer) throws BillingServicesDownException{
		services.acceptCustomerDetails(customer);
		return new ResponseEntity<>("Customer details succesfully added",HttpStatus.OK);
		
	}

	@RequestMapping(value="/acceptplanDetail",method=RequestMethod.POST,consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String>acceptplantDetail(@ModelAttribute Plan plan) throws BillingServicesDownException{
		services.getPlanAllDetails();
		return new ResponseEntity<>("plan details succesfully added",HttpStatus.OK);
		
	}
   
}
