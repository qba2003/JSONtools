package pl.put.poznan.transformer.logic.decorators;

import pl.put.poznan.transformer.logic.Json;

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
