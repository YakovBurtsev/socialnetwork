package ru.yakovburtsev.socialnetwork.user.repository;


import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovburtsev.socialnetwork.core.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return em.createNamedQuery(User.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public User get(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = em.createNamedQuery(User.GET_BY_EMAIL, User.class).setParameter(1, email).getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> findByNameAndSurname(String name, String surname) {
        String sampleName = name.isEmpty() ? "%" : name;
        String sampleSurname = surname.isEmpty() ? "%" : surname;
        return em.createNamedQuery(User.GET_BY_NAME_AND_SURNAME, User.class)
                .setParameter("name", sampleName)
                .setParameter("surname", sampleSurname)
                .getResultList();
    }
}
