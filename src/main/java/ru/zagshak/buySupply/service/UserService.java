package ru.zagshak.buySupply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.repository.userRepo.UserRepo;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepo.save(user);
    }

    public void update(User user) /*throws NotFoundException*/ {
        Assert.notNull(user, "user must not be null");
        //checkNotFoundWithId(repository.save(user), user.getId());
    }

    public void delete(int id) {
        //checkNotFoundWithId(userRepo.delete(id), id);
    }

    public User get(int id) /*throws NotFoundException*/ {
        //return checkNotFoundWithId(userRepo.get(id),id);
        return null;
    }

    public User getByEmail(String email) /*throws NotFoundException*/ {
        Assert.notNull(email, "email must not be null");
        //return checkNotFound(userRepo.getByEmail(email),"email = " + email);
        return null;
    }

    public List<User> getAll() {
        return userRepo.getAll();
    }

}
