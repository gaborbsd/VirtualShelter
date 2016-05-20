package hu.bme.aut.vshelter.dal.impl;

import hu.bme.aut.vshelter.api.VirtualShelterException;
import hu.bme.aut.vshelter.dal.InstitutionRepositoryCustom;
import hu.bme.aut.vshelter.entity.Institution;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.List;

public class InstitutionRepositoryImpl implements InstitutionRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void edit(Institution institution) throws VirtualShelterException {
        try {
            em.merge(institution);
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        } catch (TransactionRequiredException e) {
            throw new VirtualShelterException(e);
        } catch (ValidationException e) {
            throw new VirtualShelterException(e);
        }
    }

    @Override
    public List<Institution> listInstituitionsOwnedByUser(long userId) throws VirtualShelterException {
        try {
            TypedQuery<Institution> query = em.createQuery("SELECT i FROM Institution i where i.owner.id=:p",
                    Institution.class).setParameter("p", userId);
            return query.getResultList();
        } catch (IllegalArgumentException e) {
            throw new VirtualShelterException(e);
        }
    }

    @Override
    public List<Institution> listInstituitionsAdministeredByUser(long userId) throws VirtualShelterException {
        return em.createQuery("select i from Institution i, User u where u.id = :p " +
                "and (u member of i.institutionAdministrators or u = i.owner)", Institution.class)
                .setParameter("p", userId)
                .getResultList();
    }

    @Override
    public Institution checkInstituitionAdministeredByUser(long id, long userId) throws VirtualShelterException {
        try {
            return em.createQuery("select i from Institution i, User u where i.id = :id and u.id = :userId " +
                    "and (u member of i.institutionAdministrators or u = i.owner)", Institution.class)
                    .setParameter("id", id)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
