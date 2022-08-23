package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import com.csi.service.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @PostMapping("/savedata")  //webservices
    public ResponseEntity<Customer> saveData(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerServiceImpl.saveData(customer));
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Customer>> getAllData() {
        return ResponseEntity.ok(customerServiceImpl.getAllData());
    }

    @GetMapping("/getdatabyid/{custId}")
    public ResponseEntity<Optional<Customer>> getDataById(@PathVariable long custId) {
        return ResponseEntity.ok(customerServiceImpl.getDataById(custId));
    }

    @PutMapping("/updatedata/{custId}")
    public ResponseEntity<Customer> updateData(@PathVariable long custId, @RequestBody Customer customer) throws RecordNotFoundException { //customer here we passessed data from request payload or swagger UI,this customer is from Swagger UI
        // Exception Code

        Customer customer1 = customerServiceImpl.getDataById(custId).orElseThrow(() -> new RecordNotFoundException("customer ID Does Not exist")); //this customer1 is  our database

        customer1.setCustName(customer.getCustName());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustAccountBalance(customer.getCustAccountBalance());
        customer1.setCustContactNumber(customer.getCustContactNumber());
        customer1.setCustDOB(customer.getCustDOB());

        return ResponseEntity.ok(customerServiceImpl.updateData(customer1));
    }


    @DeleteMapping("/deletedata/{custId}")
    public ResponseEntity<String> deleteDataById(@PathVariable long custId) {
        customerServiceImpl.deleteData(custId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @GetMapping
    public String sayHello() {
        return "WELCOME TO CSI";
    }

    @GetMapping("/service")
    public String sayService()
    {
        return"THIS IS FINTECH";
    }
}
