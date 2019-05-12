/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.proj.model;

//import java.io.Serializable;
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
 * @author mateu
 */
@Entity
@Table(name="Restaurator")
public class Restaurator{
    @Id
    @Basic(optional=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_restauratora")
    private Integer ID_res;
    
    @Basic(optional=false)   
    @Column(name = "Login")
    private String login;
    
    @Basic(optional=false)   
    @Column(name = "Haslo")
    private String haslo;
    
    public Restaurator(){}

    public Integer getID_res() {
        return ID_res;
    }

    public void setID_res(Integer ID_res) {
        this.ID_res = ID_res;
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

    public Restaurator(String login, String haslo) {
        this.login = login;
        this.haslo = haslo;
    }
       
}