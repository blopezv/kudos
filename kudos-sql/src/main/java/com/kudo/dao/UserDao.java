package com.kudo.dao;

import com.kudo.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by brenda on 22/07/2018.
 */
public class UserDao extends AbstractDAO<User> {
    @Inject
    public UserDao(SessionFactory factory) {
        super(factory);
    }

    public List<User> getAll() {
        return query("SELECT c FROM User c " +
                " ORDER BY c.id DESC ")
                .list();
    }

    public User findById(int id) {
        return get(id);
    }

    public void delete(User person) {
        delete(person);
    }

    public void update(User person) {
        persist(person);
    }

    public User insert(User person) {
        return persist(person);
    }
}