package com.csi.dao;

import com.csi.model.Customer;
import com.csi.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerDaoImpl {

    @Autowired
    CustomerRepo customerRepoImpl;

    public Customer saveData(Customer customer){ // save only one data that's why we used a Customer
        return customerRepoImpl.save(customer);
    }

    public List<Customer> getAllData(){   // getting all data that's why we are using a list
        return customerRepoImpl.findAll();
    }

    public Optional<Customer> getDataById(long custId){ // if we used a findById then here nullpointer exception will come that's why we used optional here
        return customerRepoImpl.findById(custId);  // we used return to see the updates on responsebody
    }

    public Customer updateData(Customer customer){ // we save only one data that's why we used a customer
        return customerRepoImpl.save(customer);   // we used return to see the updates on responsebody
    }

    public void deleteData(long custId){ // we used void because we dont want a any data back
        customerRepoImpl.deleteById(custId);
    }
}
