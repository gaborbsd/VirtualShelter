package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.UserRepository;
import hu.bme.aut.vshelter.dal.UserRepositoryCustom;
import hu.bme.aut.vshelter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.ValidationException;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void edit(User user) throws VirtualShelterException {
        try {
            em.merge(user);
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        } catch (TransactionRequiredException e) {
            throw new VirtualShelterException(e);
        } catch (ValidationException e) {
            throw new VirtualShelterException(e);
        }
    }

    @Override
    @Transactional
    public void promoteUserToSiteAdministrator(long userId) throws VirtualShelterException {

        String role = "site-administrator";
        User user = userRepository.findOne(Long.valueOf(userId));

        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
        }
    }

    @Override
    @Transactional
    public void revokeUserFromSiteAdministrator(long userId) throws VirtualShelterException {

        String role = "site-administrator";
        User user = userRepository.findOne(Long.valueOf(userId));

        if (user.getRoles().contains(role)) {
            user.getRoles().remove(role);
        }
    }

    @Override
    @Transactional
    public long getUserIdfromEmail(String email) throws VirtualShelterException {
        TypedQuery<Long> query = em.createQuery(
                "SELECT u.id FROM User u WHERE u.email = ?1", Long.class)
                .setParameter(1, email);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) throws VirtualShelterException {
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.email = ?1", User.class)
                    .setParameter(1, email);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new VirtualShelterException(e);
        }

    }

}
