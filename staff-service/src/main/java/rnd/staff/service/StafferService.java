package rnd.staff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rnd.staff.entity.Staffer;
import rnd.staff.repository.StafferRepository;

import java.util.List;

@Service
public class StafferService {
    @Autowired
    private StafferRepository stafferRepository;

    public List<Staffer> getAllStaffers(){
        return stafferRepository.findAll();
    }

    public Staffer getStafferById (Long id){
        return stafferRepository.findOne(id);
    }

    public Staffer saveStaffer(Staffer staffer){
        return stafferRepository.save(staffer);
    }

    public void deleteStaffer(long id){
        stafferRepository.delete(id);
    }
}
