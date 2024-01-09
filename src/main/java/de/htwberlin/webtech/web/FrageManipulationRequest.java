package de.htwberlin.webtech.web;

public class FrageManipulationRequest {

    private String text;

    public FrageManipulationRequest(String text) {
        this.text = text;
    }
    public FrageManipulationRequest(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
