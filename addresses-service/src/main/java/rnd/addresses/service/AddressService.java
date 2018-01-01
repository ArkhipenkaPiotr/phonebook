package rnd.addresses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rnd.addresses.entity.Address;
import rnd.addresses.repository.AddressRepository;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository repository;

    @Async
    public List<Address> findAll(){
        return repository.findAll();
    }

    @Async
    public Address findAddressById(long id){
        return repository.findAddressByUserId(id);
    }

    @Async
    public Address saveAddress(Address address){
        return repository.save(address);
    }

    @Async
    public void deleteAddress(long id){
        repository.delete(id);
    }
}
