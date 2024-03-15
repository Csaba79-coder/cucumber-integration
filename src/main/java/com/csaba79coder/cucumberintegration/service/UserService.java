package com.csaba79coder.cucumberintegration.service;

import com.csaba79coder.cucumberintegration.controller.exception.UsernameAlreadyExistsException;
import com.csaba79coder.cucumberintegration.entity.User;
import com.csaba79coder.cucumberintegration.model.UserRegistrationRequest;
import com.csaba79coder.cucumberintegration.model.UserRegistrationResponse;
import com.csaba79coder.cucumberintegration.persistence.UserRepository;
import com.csaba79coder.cucumberintegration.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.csaba79coder.cucumberintegration.util.Mapper.mapUserEntityToRegResponse;
import static com.csaba79coder.cucumberintegration.util.Mapper.mapUserRegRequestToEntity;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserRegistrationResponse saveUser(UserRegistrationRequest request) {
        String username = request.getUsername();
        Optional<User> existingUser = userRepository.findUserByUsername(username);

        if (existingUser.isPresent()) {
            String message = String.format("User with username: %s already exists", username);
            log.info(message);
            throw new UsernameAlreadyExistsException(message);
        }

        User user = mapUserRegRequestToEntity(request);
        return mapUserEntityToRegResponse(userRepository.save(user));
    }
}
