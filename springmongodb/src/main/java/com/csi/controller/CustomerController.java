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

    @PostMapping("/savedata")
    public ResponseEntity<Customer> saveData(@RequestBody Customer customer){

      return ResponseEntity.ok(customerServiceImpl.saveData(customer));

    }
    @GetMapping("/getdatabyid/{custId}")
    public ResponseEntity<Optional<Customer>> getDataById(@PathVariable int custId){

        return ResponseEntity.ok(customerServiceImpl.getDataById(custId));

    }
    @GetMapping("/getalldata")
    public ResponseEntity<List<Customer>> getAllData(){
        return ResponseEntity.ok(customerServiceImpl.getAllData());
    }

    @PutMapping("/updatedata/{custId}")
    public ResponseEntity<Customer> updateData(@PathVariable int custId,@RequestBody Customer customer){

        Customer customer1 = customerServiceImpl.getDataById(custId).orElseThrow(()-> new RecordNotFoundException("customer id not exist"));

        customer1.setCustName(customer.getCustName());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustContactNumber(customer.getCustContactNumber());
        customer1.setCustAccountBalance(customer.getCustAccountBalance());



        return  ResponseEntity.ok(customerServiceImpl.updateData(customer1));
    }

    @DeleteMapping("/deletedatabyid/{custId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int custId){

        customerServiceImpl.deleteDataById(custId);
        return ResponseEntity.ok("data deleted succesfully");
    }
}
