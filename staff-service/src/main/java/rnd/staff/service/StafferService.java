package rnd.staff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rnd.staff.entity.Image;
import rnd.staff.entity.Staffer;
import rnd.staff.repository.ImageRepository;
import rnd.staff.repository.StafferRepository;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class StafferService {
    @Autowired
    private StafferRepository stafferRepository;

    @Autowired
    private ImageRepository imageRepository;

    public List<Staffer> getAllStaffers(){
        return stafferRepository.findAll();
    }

    public Staffer getStafferById (Long id){
        Staffer staffer = stafferRepository.findOne(id);
        return staffer;
    }

    public byte[] findPhotoByUrl(String url){
        return imageRepository.findOneByUrl(url).getPhoto();
    }

    public Image postPhoto(Image image){
        return imageRepository.save(image);
    }

    public Staffer saveStaffer(Staffer staffer){
        return stafferRepository.save(staffer);
    }

    public void deleteStaffer(long id){
        stafferRepository.delete(id);
    }
}
