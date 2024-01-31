package rs.ac.uns.ftn.springsecurityexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.springsecurityexample.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String activationCode);
    List<User> findByRoles_Name(String roleName);

}

