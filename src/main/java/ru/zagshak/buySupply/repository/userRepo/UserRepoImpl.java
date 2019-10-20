package ru.zagshak.buySupply.repository.userRepo;

import org.springframework.beans.factory.annotation.Autowired;
import ru.zagshak.buySupply.domain.User;

import java.util.List;

public class UserRepoImpl implements UserRepo {

    @Autowired
    private UserJpaRepo repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
}
