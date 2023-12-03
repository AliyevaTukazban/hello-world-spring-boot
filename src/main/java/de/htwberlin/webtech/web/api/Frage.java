package de.htwberlin.webtech.web.api;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Frage {

    private long id;
    private String text;
    public Frage() {
        // Standardkonstruktor-Logik hier, falls benötigt
    }
    @JsonCreator
    public Frage(@JsonProperty("id") Long id, @JsonProperty("text") String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
