package com.voatads.customer.service;

import com.voatads.customer.dto.CustomerDTO;
import com.voatads.customer.dto.UpdateCustomerDTO;
import com.voatads.customer.model.Address;
import com.voatads.customer.model.Customer;
import com.voatads.customer.repository.AddressRepository;
import com.voatads.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AddressRepository addressRepository;

    public Customer getCustomer(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Transactional
    public Customer createCustomer(CustomerDTO customerDTO){
        Customer customer = modelMapper.map(customerDTO, Customer.class);

        if(customerDTO.getAddress() != null){
            Address address = modelMapper.map(customerDTO.getAddress(), Address.class);
            addressRepository.save(address);
            customer.setAddress(address);
        }

        customerRepository.save(customer);
        return customerRepository.findById(customer.getId()).orElse(null);
    }

    public Customer saveCustomer(Customer customer){
        customerRepository.save(customer);
        return customerRepository.findById(customer.getId()).orElse(null);
    }

    @Transactional
    public Customer updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = getCustomer(id);
        if (existingCustomer != null) {
            existingCustomer.setName(customerDTO.getName());
            existingCustomer.setCpf(customerDTO.getCpf());
            existingCustomer.setEmail(customerDTO.getEmail());
            existingCustomer.setMiles(customerDTO.getMiles());

            if (customerDTO.getAddress() != null) {
                Address address = modelMapper.map(customerDTO.getAddress(), Address.class);
                addressRepository.save(address);
                existingCustomer.setAddress(address);
            }

            return saveCustomer(existingCustomer);
        }
        return null;
    }

    @Transactional
    public Customer updateCustomerFields(Long id, UpdateCustomerDTO updates) {
        Customer existingCustomer = getCustomer(id);
        if (existingCustomer != null) {
            if (updates.getName() != null) existingCustomer.setName(updates.getName());
            if (updates.getEmail() != null) existingCustomer.setEmail(updates.getEmail());
            if (updates.getCpf() != null) existingCustomer.setCpf(updates.getCpf());
            if (updates.getMiles() != null) existingCustomer.setMiles(updates.getMiles());
            if (updates.getAddress() != null) {
                Address address = modelMapper.map(updates.getAddress(), Address.class);
                addressRepository.save(address);
                existingCustomer.setAddress(address);
            }
            return saveCustomer(existingCustomer);
        }
        return null;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public String removerCustomer(Long id){
        customerRepository.deleteById(id);
        return "Customer removed with the id: " + id;
    }
}
