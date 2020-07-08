package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import net.minidev.json.JSONObject;

@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue
    public Integer id;

    public String marka;
    public String model;
    public String opis;
    public Integer cena;
    public Integer rok_produkcji;
    public String paliwo;
    public String rodzaj_nadwozia;
    public Integer pojemnosc_silnika;
    public Integer moc_silnika;
    public Integer przebieg;
    public String status;
    public Boolean accepted=false;
    public int id_uzytkownika;


    public Ad() {
        marka=null;
        model=null;
        opis=null;
        cena=null;
        rok_produkcji=null;
        paliwo=null;
        rodzaj_nadwozia=null;
        pojemnosc_silnika=null;
        moc_silnika=null;
        przebieg=null;
        status=null;
        accepted=false;
        id=null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model= model;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Integer getRok_produkcji() {
        return rok_produkcji;
    }

    public void setRok_produkcji(Integer rok_produkcji) {
        this.rok_produkcji = rok_produkcji;
    }

    public String getPaliwo() {
        return paliwo;
    }

    public void setPaliwo(String paliwo) {
        this.paliwo = paliwo;
    }

    public String getRodzaj_nadwozia() {
        return rodzaj_nadwozia;
    }

    public void setRodzaj_nadwozia(String rodzaj_nadwozia) {
        this.rodzaj_nadwozia = rodzaj_nadwozia;
    }

    public Integer getPojemnosc_silnika() {
        return pojemnosc_silnika;
    }

    public void setPojemnosc_silnika(Integer pojemnosc_silnika) {
        this.pojemnosc_silnika = pojemnosc_silnika;
    }

    public Integer getMoc_silnika() {
        return moc_silnika;
    }

    public void setMoc_silnika(Integer moc_silnika) {
        this.moc_silnika = moc_silnika;
    }

    public Integer getPrzebieg() {
        return przebieg;
    }

    public void setPrzebieg(Integer przebieg) {
        this.przebieg = przebieg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public int getId_uzytkownika() {
        return id_uzytkownika;
    }

    public void setId_uzytkownika(int id_uzytkownika) {
        this.id_uzytkownika = id_uzytkownika;
    }

    public Ad(JSONObject json){
        marka=json.get("brand").toString();
        model=json.get("model").toString();
        opis=json.get("description").toString();
        paliwo=json.get("fuel").toString();
        rodzaj_nadwozia=json.get("body_type").toString();
        status=json.get("type").toString();
        accepted=false;
        try{
            cena=Integer.parseInt(json.get("price").toString());
            rok_produkcji=Integer.parseInt(json.get("yearbook").toString());
            pojemnosc_silnika=Integer.parseInt(json.get("engine_capacity").toString());
            moc_silnika=Integer.parseInt(json.get("power").toString());
            przebieg=Integer.parseInt(json.get("mileage").toString());
        }catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }
}
