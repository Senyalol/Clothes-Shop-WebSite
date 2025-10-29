package com.ClotheShop.CShop.Repository;

import com.ClotheShop.CShop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);
    List<User> findByRole(String role);
    Optional<User> findById(int id);
    boolean existsByLogin(String login);

}
