package pl.put.poznan.JSON.logic.decorators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.JSON.logic.Json;


public class fullJsonDecorator extends JsonDecorator {

    public fullJsonDecorator(Json text){
        super(text);
    }

    @Override
    public String getData() {
        return fullJson(super.getData());
    }

    public String fullJson(String text){
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode main_json_tree = mapper.readTree(text);
           return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(main_json_tree);
        } catch (Exception e) {
            System.err.println("Error converting text to JSON: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
