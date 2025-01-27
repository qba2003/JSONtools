package pl.put.poznan.JSON.logic.decorators;

import pl.put.poznan.JSON.logic.Json;

public abstract class JsonDecorator implements Json {
    private final Json text;

    public JsonDecorator(Json text) {
        this.text = text;
    }

    @Override
    public String getData(){
        return text.getData();
    }
}
