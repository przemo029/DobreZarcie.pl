/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.proj.service;

import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import pl.polsl.proj.model.Restauracja;
import pl.polsl.proj.model.Restaurator;
import pl.polsl.proj.model.Uzytkownik;

/**
 *
 * @author mateu
 */
public class DatabaseManagement {

    @PersistenceUnit
    private EntityManagerFactory emf;

    public DatabaseManagement() {
        emf = Persistence.createEntityManagerFactory("web-jpaPU");

    }

    int id_uzyt = 0;
    int id_res = 0;

    public void dodajEncje(Object[] encja, UserTransaction transakcja) throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        EntityManager em;
        transakcja.begin();
        em = emf.createEntityManager();

        for (Object entity : encja) {
            em.persist(entity);
        }
        transakcja.commit();
        em.close();
    }

    public Uzytkownik pobierzUzytkownika(String login, String haslo) {
        EntityManager em;
        Uzytkownik uzytk;
        em = emf.createEntityManager();
        try {
            if (haslo.isEmpty()) {
                uzytk = (Uzytkownik) em.createQuery("select a FROM Uzytkownik a WHERE a.login LIKE :login")
                        .setParameter("login", login)
                        .getSingleResult();
            } else {
                uzytk = (Uzytkownik) em.createQuery("select a FROM Uzytkownik a WHERE a.login LIKE :login and a.haslo LIKE :haslo")
                        .setParameter("login", login)
                        .setParameter("haslo", haslo)
                        .getSingleResult();
            }
        } catch (NoResultException e) {
            uzytk = null;
        }
        em.close();
        return uzytk;
    }

    public Restaurator pobierzRestauratora(String login, String haslo) {
        EntityManager em;
        Restaurator rest;
        em = emf.createEntityManager();
        try {
            if (haslo.isEmpty()) {
                rest = (Restaurator) em.createQuery("SELECT a FROM Restaurator AS a WHERE a.login = :login")
                        .setParameter("login", login)
                        .getSingleResult();
            } else {
                rest = (Restaurator) em.createQuery("SELECT a FROM Restaurator AS a WHERE a.login = :login and a.haslo = :haslo ")
                        .setParameter("login", login)
                        .setParameter("haslo", haslo)
                        .getSingleResult();
            }
        } catch (NoResultException e) {
            rest = null;
        }
        em.close();
        return rest;
    }

    public List<Restauracja> pobierzRestauracje(Restaurator restaurator) {
        EntityManager em;
        List<Restauracja> res;
        em = emf.createEntityManager();
        try {
            Integer ID = restaurator.getID_res();
            res = (List<Restauracja>) em.createQuery("SELECT b FROM Restauracja AS b, Restaurator as a WHERE a.ID_res = :ID")
                    .setParameter("ID", ID)
                    .getResultList();

        } catch (NoResultException e) {
            res = null;
        }
        em.close();
        return res;
    }

    public Restauracja pobierzJednaRestauracje(Restaurator restaurator, Integer ID_REST) {
        EntityManager em;
        Restauracja res;
        em = emf.createEntityManager();
        try {
            Integer ID = restaurator.getID_res();
            res = (Restauracja) em.createQuery("SELECT b FROM Restauracja AS b, Restaurator as a WHERE a.ID_res = :ID and b.ID_restauracji = :ID_REST")
                    .setParameter("ID", ID)
                    .setParameter("ID_REST", ID_REST)
                    .getSingleResult();

        } catch (NoResultException e) {
            res = null;
        }
        em.close();
        return res;
    }

    public void aktualizujRestauracje(Restauracja rest,String adres, String nip, String konto, String tel, String kat, String nazwa,  EntityManagerFactory emf, UserTransaction utx) throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        EntityManager em;
        utx.begin();
        em = emf.createEntityManager();
        
        Integer ID = rest.getID_restauracji();
        em.createQuery("UPDATE Restauracja a SET a.adres = '" + adres + "', a.kat= '"+kat+"', a.nazwa = '"+nazwa+"',a.nip = '"+nip+"', a.nr_konta = '"+konto+"', a.tel ='"+tel+"' WHERE a.ID_restauracji = "+ID)
                .executeUpdate();
        
        utx.commit();
        em.close();

    }

    //TODO
    //public Restauracja dodajRestauracje
    //public Zamowienie dodajzamowienie
    //public Menu dodajPozycje
}
