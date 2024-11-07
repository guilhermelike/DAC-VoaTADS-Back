package com.voatads.customer.service;

import com.voatads.customer.dto.CustomerDTO;
import com.voatads.customer.dto.UpdateCustomerDTO;
import com.voatads.customer.dto.TransactionDTO;
import com.voatads.customer.model.Customer;
import com.voatads.customer.model.Transaction;
import com.voatads.customer.repository.CustomerRepository;
import com.voatads.customer.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransactionRepository transactionRepository;

    public Customer getCustomer(UUID id){
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Transactional
    public Customer createCustomer(CustomerDTO customerDTO){
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customerRepository.save(customer);
        return customerRepository.findById(customer.getId()).orElse(null);
    }

    public Customer saveCustomer(Customer customer){
        customerRepository.save(customer);
        return customerRepository.findById(customer.getId()).orElse(null);
    }

    @Transactional
    public Customer updateCustomer(UUID id, CustomerDTO customerDTO) {
        Customer existingCustomer = getCustomer(id);
        if (existingCustomer != null) {
            modelMapper.map(customerDTO, existingCustomer);
            return saveCustomer(existingCustomer);
        }
        return null;
    }

    @Transactional
    public Customer updateCustomerFields(UUID id, UpdateCustomerDTO updates) {
        Customer existingCustomer = getCustomer(id);
        if (existingCustomer != null) {
            modelMapper.map(updates, existingCustomer);
            return saveCustomer(existingCustomer);
        }
        return null;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public String removeCustomer(UUID id){
        customerRepository.deleteById(id);
        return "Customer removed with the id: " + id;
    }

    @Transactional
    public Transaction createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        Customer customer = getCustomer(transactionDTO.getCustomerId());
        if (customer != null) {
            transaction.setCustomer(customer);
            return transactionRepository.save(transaction);
        }
        return null;
    }

    public List<Transaction> getTransactionsByCustomer(UUID customerId) {
        return transactionRepository.findAllByCustomerId(customerId);
    }

    @Transactional
    public Customer updateMiles(UUID id, double miles, boolean isBuying) {
        Customer customer = getCustomer(id);
        if (customer != null) {
            double updatedMiles = isBuying ? customer.getMiles() + miles : customer.getMiles() - miles;
            customer.setMiles(updatedMiles);
            return saveCustomer(customer);
        }
        return null;
    }

}