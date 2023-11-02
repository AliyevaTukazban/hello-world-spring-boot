package de.htwberlin.webtech.web;

import jakarta.persistence.*;

@Entity
@Table(name = "schueler")
public class Schueler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "vorname")
    private String vorname;

    @Column(name = "nachname")
    private String nachname;

    @Column(name = "klasse")
    private String klasse;

    // Standardkonstruktor
    public Schueler() {
    }

    // Konstruktor mit Parametern
    public Schueler(String vorname, String nachname, String klasse) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.klasse = klasse;
    }

    // Getter und Setter für 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter und Setter für 'vorname'
    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    // Getter und Setter für 'nachname'
    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    // Getter und Setter für 'klasse'
    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }
    public String getFullName() {
        return vorname + " " + nachname;
    }

    public void updateSchuelerDetails(String vorname, String nachname, String klasse) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.klasse = klasse;
    }

    // Weitere benutzerdefinierte Methoden hier

    @Override
    public String toString() {
        return "Schueler [id=" + id + ", vorname=" + vorname + ", nachname=" + nachname + ", klasse=" + klasse + "]";
    }

    // Weitere Methoden, falls benötigt
}
