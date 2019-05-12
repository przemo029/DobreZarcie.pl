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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import lombok.*;
/**
 *
 * @author mateu
 */
@Entity
@Table(name="Konsultanci")
public class Konsultant {
    @Id
    @Basic(optional=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ukonsultanta")
    private Integer ID_kons;
    
    @Basic(optional=false)   
    @Column(name = "Login")
    private String login;
    
    @Basic(optional=false)   
    @Column(name = "Haslo")
    private String haslo;
    
    public Konsultant(){}

    public Integer getID_kons() {
        return ID_kons;
    }

    public void setID_kons(Integer ID_kons) {
        this.ID_kons = ID_kons;
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

    public Konsultant(String login, String haslo) {
        this.login = login;
        this.haslo = haslo;
    }
    
       
}