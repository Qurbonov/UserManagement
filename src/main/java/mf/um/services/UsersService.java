package mf.um.services;

import mf.um.domain.Users;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qurbonov on 9/22/2015.
 */
public interface UsersService {
    List<Users> findAll();

    Users findOne(Long id);

    long count();

    Users findByName(String username);

    Users save(Users user);

    void delete(Long userID);
}
