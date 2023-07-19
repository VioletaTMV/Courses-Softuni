package org.example.XMLprocessing.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.XMLprocessing.entities.users.User;
import org.example.XMLprocessing.entities.users.UserWithProductsSoldAndBuyerInfoDTO;
import org.example.XMLprocessing.entities.users.UsersWithProductsSoldDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



    List<User> findByProductsSoldBuyerIsNotNullOrderByLastNameAscFirstNameAsc();

    @Query(value = "SELECT u " +
            "FROM User u " +
            "JOIN u.productsSold ps " +
            "WHERE ps.buyer IS NOT NULL " +
            "GROUP BY u.id " +
            "ORDER BY count(ps) DESC, u.lastName ASC")
    List<User> findByProductsSoldBuyerNotNullOrderByProductCountLastName();
}
