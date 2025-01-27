package pl.put.poznan.JSON.logic.decorators;

import pl.put.poznan.JSON.logic.JSON;

public abstract class JSONDecorator implements JSON {
    private final JSON text;

    public JSONDecorator(JSON text) {
        this.text = text;
    }

    @Override
    public String getData(){
        return text.getData();
    }
}
