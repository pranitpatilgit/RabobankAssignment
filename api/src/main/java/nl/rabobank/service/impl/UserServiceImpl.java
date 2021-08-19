package nl.rabobank.service.impl;

import nl.rabobank.dto.User;
import nl.rabobank.exception.NotFoundException;
import nl.rabobank.mongo.dao.UserRepository;
import nl.rabobank.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User getUserByName(String name) {
        nl.rabobank.mongo.entity.User userEntity = userRepository.findByName(name).orElseThrow(() -> new NotFoundException("User with name - " + name + " is not found."));
        return modelMapper.map(userEntity, User.class);
    }

    @Override
    public User createUser(User user) {
        nl.rabobank.mongo.entity.User userEntity = userRepository.save(modelMapper.map(user, nl.rabobank.mongo.entity.User.class));
        return modelMapper.map(userEntity, User.class);
    }
}
