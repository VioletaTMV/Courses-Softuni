package org.example.XMLprocessing.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jdk.swing.interop.SwingInterOpUtils;
import org.example.XMLprocessing.entities.products.Product;
import org.example.XMLprocessing.entities.products.SoldProductDTO;
import org.example.XMLprocessing.entities.products.SoldProductsWithCountDTO;
import org.example.XMLprocessing.entities.users.*;
import org.example.XMLprocessing.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.XMLprocessing.constants.ErrorMessages.INVALID_ID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public long getUserRepositorySize() {

        return this.userRepository.count();

    }

    @Override
    public void saveAllToDB(List<User> usersToBeAddedToDB) {

        this.userRepository.saveAll(usersToBeAddedToDB);
    }

    @Override
    public User getUserByID(long randomUserId) {

        return this.userRepository.findById(randomUserId)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ID));
    }

    @Override
    @Transactional
    public UserWithProductsSoldAndBuyerInfoWrapperDTO getSellersWithProductsSoldInfoAndTheirBuyer() throws IOException {

        List<User> usersByProductsSoldNotNull = userRepository.findByProductsSoldBuyerIsNotNullOrderByLastNameAscFirstNameAsc();

        usersByProductsSoldNotNull.forEach(user -> {
            Set<Product> productsToSell = user.getProductsSold();
            Set<Product> productsActuallySold = productsToSell.stream().filter(product -> product.getBuyer() != null).collect(Collectors.toSet());
            user.setProductsSold(productsActuallySold);
        });

        List<UserWithProductsSoldAndBuyerInfoDTO> userWithProductsSoldAndBuyerInfoDTOS = usersByProductsSoldNotNull.stream()
                .map(user -> modelMapper.map(user, UserWithProductsSoldAndBuyerInfoDTO.class))
                .toList();

        UserWithProductsSoldAndBuyerInfoWrapperDTO userWithProductsSoldAndBuyerInfoWrapperDTO = new UserWithProductsSoldAndBuyerInfoWrapperDTO(userWithProductsSoldAndBuyerInfoDTOS);

        return userWithProductsSoldAndBuyerInfoWrapperDTO;


    }

    @Override
    @Transactional
    public UsersWithProductsSoldWrapperDTO findUsersAndTheirProductsSold() throws IOException {

        List<User> pureUsers = userRepository.findByProductsSoldBuyerNotNullOrderByProductCountLastName();

        pureUsers.forEach(user -> {
            Set<Product> productsToSell = user.getProductsSold();
            Set<Product> productsActuallySold = productsToSell.stream().filter(product -> product.getBuyer() != null).collect(Collectors.toSet());
            user.setProductsSold(productsActuallySold);
        });

        List<UsersWithProductsSoldDTO> usersWithProductsSoldDTOS = pureUsers.stream()
                .map(user -> {
                    SoldProductDTO[] soldProductsPerUser = modelMapper.map(user.getProductsSold(), SoldProductDTO[].class);
                    SoldProductsWithCountDTO soldProductsWithCountDTO = new SoldProductsWithCountDTO(Arrays.stream(soldProductsPerUser).toList());
                    UsersWithProductsSoldDTO usersWithProductsSoldDTO = modelMapper.map(user, UsersWithProductsSoldDTO.class);
                    usersWithProductsSoldDTO.setSoldProductsWrapperDTO(soldProductsWithCountDTO);
                    return usersWithProductsSoldDTO;
                })
                .toList();

        UsersWithProductsSoldWrapperDTO usersWithCount = new UsersWithProductsSoldWrapperDTO(usersWithProductsSoldDTOS);

        return usersWithCount;
    }
}
