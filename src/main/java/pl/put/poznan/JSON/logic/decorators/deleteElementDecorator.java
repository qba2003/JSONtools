package pl.put.poznan.JSON.logic.decorators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import pl.put.poznan.JSON.logic.Json;

public class deleteElementDecorator extends JsonDecorator {

    private String attributes;

    public deleteElementDecorator(Json text){
        super(text);
    }
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String[] getDataDeleted(){
        return delete_element(super.getData(),attributes);
    }

    public String [] delete_element(String json_text,  String attributes) {
        ObjectMapper mapper = new ObjectMapper();
        String[] splitted = attributes.split(",");
        String successful_remove = "Removed attributes: ";
        String[] final_output = new String[2];
        try {
            JsonNode json = mapper.readTree(json_text);
            for (String s : splitted) {
                if(json.get(s.trim()) != null) {
                    ((ObjectNode) json).remove(s.trim());
                    successful_remove = successful_remove + s.trim() + ", ";
                }
            }
            final_output[0] = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            if(successful_remove.equals("Removed attributes: ")) {

                final_output[1] = "None of given attributes were found in JSON file.";
            } else {
                successful_remove = successful_remove.substring(0, successful_remove.length() - 2);
                final_output[1] = successful_remove;
            }
            return final_output;
        } catch (Exception e) {
            System.err.println("Error while removing attributes: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
