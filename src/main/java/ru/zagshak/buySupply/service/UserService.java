package ru.zagshak.buySupply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.repository.userRepo.UserRepo;
import ru.zagshak.buySupply.util.exception.NotFoundException;

import java.util.List;

import static ru.zagshak.buySupply.util.ValidationUtil.checkNotFound;
import static ru.zagshak.buySupply.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepo.save(user);
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(userRepo.save(user), user.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(userRepo.delete(id), id);
    }

    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(userRepo.get(id),id);

    }

    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepo.getByEmail(email),"email = " + email);
    }

    public List<User> getAll() {
        return userRepo.getAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return getByEmail(s);
    }
}
