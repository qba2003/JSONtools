package pl.put.poznan.JSON.logic;

public class JSONimpl implements JSON {
    private final String data;

    public JSONimpl(String data) {
        this.data = data;
    }

    @Override
    public String getData(){
        return data;
    }
}
