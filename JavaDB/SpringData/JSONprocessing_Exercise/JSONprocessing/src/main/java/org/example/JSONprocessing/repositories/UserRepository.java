package org.example.JSONprocessing.repositories;

import org.example.JSONprocessing.entities.products.SoldProductDTO;
import org.example.JSONprocessing.entities.users.User;
import org.example.JSONprocessing.entities.users.UsersWithProductsSoldDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    List<User> findByProductsSoldBuyerNotNullOrderByLastNameAscFirstNameAsc();

    @Query(value = "SELECT new org.example.JSONprocessing.entities.users.UsersWithProductsSoldDTO (u.firstName, u.lastName,  u.age) " +
            "FROM User u " +
            "JOIN u.productsSold ps " +
            "WHERE ps.buyer IS NOT NULL " +
            "GROUP BY u.id " +
           "ORDER BY count(ps) DESC, u.lastName ASC")
    List<UsersWithProductsSoldDTO> findByProductsSoldBuyerNotNullOrderByProductsSizesDesc();

   // List<SoldProductDTO> getProductsSoldAndPriceByUser(UsersWithProductsSoldDTO user);

    @Query(value = "SELECT u " +
            "FROM User u " +
            "JOIN u.productsSold ps " +
            "WHERE ps.buyer IS NOT NULL " +
            "GROUP BY u.id " +
            "ORDER BY count(ps) DESC, u.lastName ASC")
    List<User> findByProductsSoldBuyerNotNullOrderByProductCountLastName();
}
