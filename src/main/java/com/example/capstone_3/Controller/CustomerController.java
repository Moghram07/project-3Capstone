package com.example.capstone_3.Controller;

import com.example.capstone_3.Api.ApiResponse;
import com.example.capstone_3.Model.Customer;
import com.example.capstone_3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@Valid @RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer added successfully"));
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity updateCustomer(@PathVariable Integer customerId, @Valid @RequestBody Customer customer) {
        customerService.updateCustomer(customerId,customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer updated successfully"));
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted successfully"));
    }

    @GetMapping("/orders/{customerId}")
    public ResponseEntity getAllOrders(@PathVariable Integer customerId) {
        return ResponseEntity.status(200).body(customerService.getOrders(customerId));
    }

    @PutMapping("/buy/{customerId}/{fabricId}/{MerchantId}/{tailorId}/{designerId}/{meter}")
    public ResponseEntity buy(@PathVariable Integer customerId, @PathVariable Integer fabricId ,@PathVariable Integer MerchantId ,@PathVariable Integer tailorId ,@PathVariable Integer designerId ,@PathVariable double meter){
        customerService.makeOrder(customerId,fabricId,MerchantId,tailorId,designerId,meter);
        return ResponseEntity.status(200).body(new ApiResponse("Order made successfully"));
    }

}
