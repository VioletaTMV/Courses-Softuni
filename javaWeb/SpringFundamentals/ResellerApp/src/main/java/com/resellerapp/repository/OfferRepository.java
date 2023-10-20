package com.resellerapp.repository;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllBySellerIdAndBuyerNull(Long id);

    List<Offer> findByBuyerId(Long id);

    List<Offer> findBySellerIdNotAndBuyerNull(Long id);

    void delete(Offer offer);
}
