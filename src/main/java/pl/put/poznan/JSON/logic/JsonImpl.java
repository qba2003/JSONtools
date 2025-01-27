package pl.put.poznan.JSON.logic;

public class JsonImpl implements Json {
    private final String data;

    public JsonImpl(String data) {
        this.data = data;
    }

    @Override
    public String getData(){
        return data;
    }
}
