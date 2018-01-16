package rnd.addresses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import rnd.addresses.entity.Address;
import rnd.addresses.service.AddressService;
import sun.security.krb5.internal.KRBCred;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    AddressService addressService;

    @CrossOrigin
    @RequestMapping(value = "/addresses",method = RequestMethod.GET)
    private ResponseEntity<List<Address>> getAllAddressess(){
        List<Address> addresses = addressService.findAll();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/addresses/{id}", method = RequestMethod.GET)
    private ResponseEntity<Address> getAddress(@PathVariable("id") Long id){
        return new ResponseEntity<>(addressService.findAddressById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/addresses", method = RequestMethod.POST)
    private ResponseEntity<Address> saveAddress(@RequestBody Address address){
        return new ResponseEntity<>(addressService.saveAddress(address),HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/addresses/{id}", method = RequestMethod.DELETE)
    private ResponseEntity<Address> deleteAddress(@PathVariable("id") Long id){
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
