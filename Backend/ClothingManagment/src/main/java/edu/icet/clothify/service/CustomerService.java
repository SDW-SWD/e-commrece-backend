package edu.icet.clothify.service;

import edu.icet.clothify.dto.CustomerDto;
import edu.icet.clothify.entity.Customer;

public interface CustomerService{
    Boolean addCustomer(CustomerDto customerDto);
    Customer updateCustomer(Long id , CustomerDto customerDto);
    CustomerDto getCustomerByName(String name);

//    CustomerDto getCustomerById(Long id);

}
