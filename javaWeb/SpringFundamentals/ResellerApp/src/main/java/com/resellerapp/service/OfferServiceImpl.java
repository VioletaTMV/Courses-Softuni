package com.resellerapp.service;

import com.resellerapp.model.DTOs.OfferCreateDTO;
import com.resellerapp.model.DTOs.OfferDTO;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.enums.ConditionNameEnum;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl {

    private OfferRepository offerRepository;
    private ModelMapper modelMapper;
    private CurrentUser currentUser;
    private AuthServiceImpl userService;
    private ConditionServiceImpl conditionService;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, CurrentUser currentUser, AuthServiceImpl userService, ConditionServiceImpl conditionService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.conditionService = conditionService;
    }


    public boolean create(OfferCreateDTO offerCreateDTO) {

        Optional<User> seller = this.userService.findById(this.currentUser.getId());

        if (seller.isEmpty()) {
            return false;
        }

        Offer newOffer = this.modelMapper.map(offerCreateDTO, Offer.class)
                .setSeller(seller.get())
                .setCondition(this.conditionService.getCondition(ConditionNameEnum.valueOf(offerCreateDTO.getCondition())));

        this.offerRepository.save(newOffer);

        return true;
    }

    public List<OfferDTO> getOwnedOffers(Long id) {

        List<Offer> allBySellerId = this.offerRepository.findAllBySellerIdAndBuyerNull(id);

        List<OfferDTO> ownedOffers = allBySellerId.stream()
                .map(OfferDTO::new)
                .collect(Collectors.toList());

        return ownedOffers;

    }

    public List<OfferDTO> getBoughtOffers(Long id) {

        List<Offer> allByBuyerId = this.offerRepository.findByBuyerId(id);

        List<OfferDTO> boughtOffersDTOs = allByBuyerId.stream()
                .map(OfferDTO::new)
                .collect(Collectors.toList());

        return boughtOffersDTOs;
    }

    public List<OfferDTO> getOthersOffers(Long id) {

        List<Offer> allBySellerIdNotAndBuyerIdNull = this.offerRepository.findBySellerIdNotAndBuyerNull(id);

        List<OfferDTO> othersOffers = allBySellerIdNotAndBuyerIdNull.stream()
                .map(o -> new OfferDTO(o))
                .collect(Collectors.toList());

        return othersOffers;
    }

    @Transactional
    public boolean updateOfferWithBuyer(Long id) {

        Optional<Offer> offerToBuy = this.offerRepository.findById(id);
        Optional<User> buyer = this.userService.findById(this.currentUser.getId());

        if (offerToBuy.get().getBuyer() != null || buyer.isEmpty()) {
            return false;
        }

        offerToBuy.get().setBuyer(buyer.get());
        this.offerRepository.save(offerToBuy.get());

        return true;
    }

    public void deleteOffer(Long id) {
        Optional<Offer> offerToDelete = this.offerRepository.findById(id);

        if (!offerToDelete.isPresent()){
         throw new NoSuchElementException();
        }

        this.offerRepository.delete(offerToDelete.get());
    }
}
