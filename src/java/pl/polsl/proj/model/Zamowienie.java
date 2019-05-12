/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.proj.model;

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

/**
 *
 * @author mateu
 */
@Entity
@Table(name="Zamowienie")
public class Zamowienie {
    
    @Id
    @Basic(optional=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_zamowienia")
    private Integer ID_zamowienia;
    
    @JoinColumn(name="Nazwa",referencedColumnName = "Nazwa")
    @ManyToOne
    private Restauracja restauracja;
    
//    @JoinColumn(name="ID_dania",referencedColumnName = "ID_dania")
//    @ManyToOne
//    private List<Menu> zamowienie;
    
    @JoinColumn(name="ID_uzytkownika",referencedColumnName = "ID_uzytkownika")
    @ManyToOne
    private Uzytkownik ID_uzytkownia;
    
    public Zamowienie(){}

    public Integer getID_zamowienia() {
        return ID_zamowienia;
    }

    public void setID_zamowienia(Integer ID_zamowienia) {
        this.ID_zamowienia = ID_zamowienia;
    }

    public Restauracja getRestauracja() {
        return restauracja;
    }

    public void setRestauracja(Restauracja restauracja) {
        this.restauracja = restauracja;
    }

//    public List<Menu> getZamowienie() {
//        return zamowienie;
//    }
//
//    public void setZamowienie(List<Menu> zmowienie) {
//        this.zamowienie = zmowienie;
//    }

    public Uzytkownik getID_uzytkownia() {
        return ID_uzytkownia;
    }

    public void setID_uzytkownia(Uzytkownik ID_uzytkownia) {
        this.ID_uzytkownia = ID_uzytkownia;
    }

    public Zamowienie(Integer ID_zamowienia, Restauracja restauracja, List<Menu> zmowienie, Uzytkownik ID_uzytkownia) {
        this.ID_zamowienia = ID_zamowienia;
        this.restauracja = restauracja;
        //this.zamowienie = zmowienie;
        this.ID_uzytkownia = ID_uzytkownia;
    }
    
    
}
