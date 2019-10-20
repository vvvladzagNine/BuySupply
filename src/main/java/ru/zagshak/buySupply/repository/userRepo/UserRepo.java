package ru.zagshak.buySupply.repository.userRepo;

import ru.zagshak.buySupply.domain.User;

import java.util.List;

public interface UserRepo {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();
}
