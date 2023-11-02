package de.htwberlin.webtech.persistence;


import jakarta.persistence.*;

@Entity(name = "fragen" )
public class FrageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "text", nullable = false)
    private String text;

    public FrageEntity(String text) {
        this.text = text;
    }

    protected FrageEntity() {
    }

    public long getId() {
        return id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
