package rnd.addresses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rnd.addresses.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByUserId (long id);
}
