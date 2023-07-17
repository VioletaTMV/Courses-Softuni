package org.example.JSONprocessing.services;

import com.google.gson.Gson;
import org.example.JSONprocessing.entities.products.SoldProductDTO;
import org.example.JSONprocessing.entities.products.SoldProductsWithCountDTO;
import org.example.JSONprocessing.entities.users.User;
import org.example.JSONprocessing.entities.users.UserWithProductsSoldAndBuyerInfoDTO;
import org.example.JSONprocessing.entities.users.UsersWithProductsSoldDTO;
import org.example.JSONprocessing.entities.users.UsersWithProductsSoldWrapperDTO;
import org.example.JSONprocessing.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.example.JSONprocessing.constants.ErrorMessages.INVALID_ID;
import static org.example.JSONprocessing.constants.FilePath.OUTPUT_PATH_2;
import static org.example.JSONprocessing.constants.FilePath.OUTPUT_PATH_4;
import static org.example.JSONprocessing.constants.Utils.writeIntoJsonFile;

@Service
public class UserServiceImpl implements UserService {

    private Gson gson;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(Gson gson, UserRepository userRepository, ModelMapper modelMapper) {
        this.gson = gson;
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
    public List<UserWithProductsSoldAndBuyerInfoDTO> getSellersWithProductsSoldInfoAndTheirBuyer() throws IOException {

        List<User> usersByProductsSoldNotNull = userRepository.findByProductsSoldBuyerNotNullOrderByLastNameAscFirstNameAsc();

        List<UserWithProductsSoldAndBuyerInfoDTO> userWithProductsSoldAndBuyerInfoDTOS = usersByProductsSoldNotNull.stream()
                .map(user -> modelMapper.map(user, UserWithProductsSoldAndBuyerInfoDTO.class))
                .toList();

        String jsonReady = gson.toJson(userWithProductsSoldAndBuyerInfoDTOS);
        System.out.println(jsonReady);

        //и принтиране във файл също
        writeIntoJsonFile(jsonReady, OUTPUT_PATH_2);

        return userWithProductsSoldAndBuyerInfoDTOS;


    }

    @Override
    @Transactional
    public UsersWithProductsSoldWrapperDTO findUsersAndTheirProductsSold() throws IOException {

        //      List<UsersWithProductsSoldDTO> users = userRepository.findByProductsSoldBuyerNotNullOrderByProductsSizesDesc();
        List<User> pureUsers = userRepository.findByProductsSoldBuyerNotNullOrderByProductCountLastName();

//        SoldProductDTO[] test = modelMapper.map(pureUsers.get(0).getProductsSold(), SoldProductDTO[].class);
//        SoldProductsWithCountDTO testWithCount = new SoldProductsWithCountDTO(Arrays.stream(test).toList());

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

        String jsonReadyUsersWithCount = gson.toJson(usersWithCount);

        System.out.println(jsonReadyUsersWithCount);

        //и принтираме във файл също
        writeIntoJsonFile(jsonReadyUsersWithCount, OUTPUT_PATH_4);

        return usersWithCount;
    }
}
