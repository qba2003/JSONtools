package pl.put.poznan.JSON.logic.decorators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.JSON.logic.JSON;

public class minifyDecorator extends JSONDecorator {

    public minifyDecorator(JSON text){
        super(text);
    }

    @Override
    public String getData(){
        return minify(super.getData());
    }

    public String minify(String text){
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode main_json_tree = mapper.readTree(text);
            return mapper.writeValueAsString(main_json_tree);
        } catch (Exception e) {
            System.err.println("Error converting text to JSON: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
