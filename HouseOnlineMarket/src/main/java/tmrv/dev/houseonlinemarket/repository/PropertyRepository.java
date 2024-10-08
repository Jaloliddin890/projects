package tmrv.dev.houseonlinemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tmrv.dev.houseonlinemarket.entities.Property;

import java.util.List;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {



}