package rs.ac.uns.ftn.springsecurityexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.springsecurityexample.model.Company;
import rs.ac.uns.ftn.springsecurityexample.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>{
    List<Equipment> findByNameContainingIgnoreCase(String name);

}
