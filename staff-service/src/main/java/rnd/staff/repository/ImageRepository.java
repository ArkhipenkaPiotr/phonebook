package rnd.staff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rnd.staff.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    public Image findOneByUrl(String url);
}
