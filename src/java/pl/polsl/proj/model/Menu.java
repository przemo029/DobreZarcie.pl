/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.proj.model;

import java.util.ArrayList;
import java.util.List;
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
@Table(name="Menu")
public class Menu {
    @Id
    @Basic(optional=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_dania")
    private Integer ID_dania;
    
    @Basic(optional = false)
    @Column(name = "Nazwa")
    private String nazwadania;
    
    @Basic(optional = false)
    @Column(name = "Cena")
    private String cena;
    
    @Basic(optional = false)
    @Column(name = "Wczesniejszeceny")
    private List<String> wczesniejszeceny;
    
    @JoinColumn(name="ID_restauracji",referencedColumnName = "ID_restauracji")
    @ManyToOne
    private Restauracja restauracja;
    
    public Menu(){}

    public Integer getID_dania() {
        return ID_dania;
    }

    public void setID_dania(Integer ID_dania) {
        this.ID_dania = ID_dania;
    }

    public String getNazwadania() {
        return nazwadania;
    }

    public void setNazwadania(String nazwadania) {
        this.nazwadania = nazwadania;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public List<String> getWczesniejszeceny() {
        return wczesniejszeceny;
    }

    public void setWczesniejszeceny(List<String> wczesniejszeceny) {
        this.wczesniejszeceny = wczesniejszeceny;
    }

    public Restauracja getRestauracja() {
        return restauracja;
    }

    public void setRestauracja(Restauracja restauracja) {
        this.restauracja = restauracja;
    }

    public Menu(Integer ID_dania, String nazwadania, String cena, List<String> wczesniejszeceny, Restauracja restauracja) {
        this.ID_dania = ID_dania;
        this.nazwadania = nazwadania;
        this.cena = cena;
        this.wczesniejszeceny = wczesniejszeceny;
        this.restauracja = restauracja;
    }
    
}
