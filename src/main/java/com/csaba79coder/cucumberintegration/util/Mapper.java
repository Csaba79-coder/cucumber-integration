package com.csaba79coder.cucumberintegration.util;

import com.csaba79coder.cucumberintegration.entity.User;
import com.csaba79coder.cucumberintegration.model.UserRegistrationRequest;
import com.csaba79coder.cucumberintegration.model.UserRegistrationResponse;
import org.modelmapper.ModelMapper;

public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static User mapUserRegRequestToEntity(UserRegistrationRequest request) {
        User user = new User();
        modelMapper.map(request, user);
        return user;
    }

    public static UserRegistrationResponse mapUserEntityToRegResponse(User user) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        modelMapper.map(user, response);
        return response;
    }

    private Mapper() {

    }
}
