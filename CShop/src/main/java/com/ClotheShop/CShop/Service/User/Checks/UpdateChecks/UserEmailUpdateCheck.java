package com.ClotheShop.CShop.Service.User.Checks.UpdateChecks;

import com.ClotheShop.CShop.DTO.UserDTO.VerifyChangeDTO;
import com.ClotheShop.CShop.Entity.User;
import com.ClotheShop.CShop.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserEmailUpdateCheck implements UserUpdateCheck{

    private static final Logger LOGGER = LogManager.getLogger(UserEmailUpdateCheck.class);

    private UserRepository userRepository;

    public UserEmailUpdateCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void upCheck(User certainUser, User newData) {

        if(newData != null && newData.getEmail() != null && !newData.getEmail().isEmpty() && !userRepository.findByEmail(newData.getEmail()).isPresent()){

            String oldEmail = certainUser.getEmail();
            certainUser.setEmail(newData.getEmail());
            LOGGER.info("{} User Email was updated from {} to {}",certainUser.getId(),oldEmail,newData.getEmail());

        }

    }

    @Override
    public void upUserCheck(User certainUser, VerifyChangeDTO verifyChangeDTO) {

        if(verifyChangeDTO != null && verifyChangeDTO.getEmail() != null && !userRepository.findByEmail(verifyChangeDTO.getEmail()).isPresent() && !verifyChangeDTO.getEmail().isEmpty()){

            String oldEmail = certainUser.getEmail();
            certainUser.setEmail(verifyChangeDTO.getEmail());
            LOGGER.info("{} User Email was updated from {} to {}",certainUser.getId(),oldEmail,verifyChangeDTO.getEmail());

        }

    }
}
