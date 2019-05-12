/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.proj.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import lombok.*;

/**
 *
 * @author Mateusz
 */
@Entity
@Table(name="Uzytkownik")
public class Uzytkownik {
    @Id
    @Basic(optional=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_uzytkownika")
    private Integer ID_uzyt;
    
    @Basic(optional=false)   
    @Column(name = "Login")
    private String login;
    
    @Basic(optional=false)   
    @Column(name = "Haslo")
    private String haslo;
    
    public Uzytkownik(){};

    public Integer getID_uzyt() {
        return ID_uzyt;
    }

    public void setID_uzyt(Integer ID_uzyt) {
        this.ID_uzyt = ID_uzyt;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public Uzytkownik(String login, String haslo) {
        this.login = login;
        this.haslo = haslo;
    }
       
}
