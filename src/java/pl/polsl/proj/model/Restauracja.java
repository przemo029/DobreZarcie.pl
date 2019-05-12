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
@Table(name="Restauracje")
public class Restauracja {
    @Id
    @Basic(optional=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_restauracji")
    private Integer ID_restauracji;
    
    
    @Basic(optional = false)
    @Column(name = "Nazwa")
    private String nazwa;
    
    @Basic(optional = false)
    @Column(name = "Adres")
    private String adres;
    
    @Basic(optional = false)
    @Column(name = "Tel")
    private String tel;
    
    @Basic(optional = false)
    @Column(name = "Kategoria")
    private String kat;
    
    @Basic(optional = false)
    @Column(name = "NIP")
    private String nip;
    
    @Basic(optional = false)
    @Column(name = "Konto")
    private String nr_konta;
    
    @JoinColumn(name="ID_restauratora",referencedColumnName = "ID_restauratora")
    @ManyToOne
    private Restaurator restaurator;
    
    public Restauracja(){};

    public Integer getID_restauracji() {
        return ID_restauracji;
    }

    public void setID_restauracji(Integer ID_restauracji) {
        this.ID_restauracji = ID_restauracji;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getKat() {
        return kat;
    }

    public void setKat(String kat) {
        this.kat = kat;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNr_konta() {
        return nr_konta;
    }

    public void setNr_konta(String nr_konta) {
        this.nr_konta = nr_konta;
    }

    public Restaurator getRestaurator() {
        return restaurator;
    }

    public void setRestaurator(Restaurator restaurator) {
        this.restaurator = restaurator;
    }

    public Restauracja( String nazwa, String adres, String tel, String kat, String nip, String nr_konta, Restaurator restaurator) {
        //this.ID_restauracji = ID_restauracji;
        this.nazwa = nazwa;
        this.adres = adres;
        this.tel = tel;
        this.kat = kat;
        this.nip = nip;
        this.nr_konta = nr_konta;
        this.restaurator = restaurator;
    }
    
    
    
}