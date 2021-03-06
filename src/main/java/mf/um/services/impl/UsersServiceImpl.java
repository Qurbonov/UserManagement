package mf.um.services.impl;

import mf.um.controllers.Credentials;
import mf.um.domain.Users;
import mf.um.repositories.UserRepository;
import mf.um.services.UsersService;
import mf.um.spec.UserSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public List<Users> findAll() {
        return userRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
    }

    @Override
    public Users findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public Users findByName(String username) {
        return userRepository.findOne(UserSpec.byUsername(username));
    }

    @Override
    public Users save(Users user) {
        return userRepository.save(user);
    }

    //    @Override
    public Users save(String username, Users user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long userID) {
        userRepository.delete(userID);
    }

    @Override
    public Users findUsers(Credentials credentials) {
        return findByName(credentials.getUsername());
    }

}
