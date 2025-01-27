package pl.put.poznan.JSON.logic.decorators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.JSON.logic.Json;

public class showSelectedDecorator extends JsonDecorator {

    private String attributes;
    public showSelectedDecorator(Json text) {
        super(text);
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String[] getDataSelected(){
        return show_selected(super.getData(),attributes);
    }

    public String [] show_selected(String json_text,  String attributes){
        ObjectMapper mapper = new ObjectMapper();
        String[] final_output = new String[2];

        String[] splitted = attributes.split(",");
        String left_attributes = "";
        String jsonText = "{";
        String fianl_atributes = "Wybrane atrubyty: ";


        try {
            JsonNode json = mapper.readTree(json_text);
            for (String s : splitted) {
                JsonNode single_node = json.get(s.trim());
                if (single_node != null) {
                    fianl_atributes = fianl_atributes + s.trim() + ',';
                    jsonText = jsonText + '"' + s.trim() + '"' + " : " + mapper.writeValueAsString(single_node) + ",";
                } else {
                    left_attributes = left_attributes + s.trim() + ", ";
                }

            }

            if(jsonText.equals("{")) {
                fianl_atributes = "Żaden z podanych argumentów nie znajduje się w dnaych";
                jsonText = "";
            } else {
                jsonText = jsonText.substring(0, jsonText.length() - 1) + '}';

                fianl_atributes = fianl_atributes.substring(0, fianl_atributes.length() - 1);

                if(!left_attributes.isEmpty()) {
                    left_attributes = left_attributes.substring(0, left_attributes.length() - 2);

                    fianl_atributes = fianl_atributes + ". Źle podano atrybuty: " + left_attributes + '.';
                }
            }
            JsonNode final_json = mapper.readTree(jsonText);

            final_output[0] = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(final_json);
            final_output[1] = fianl_atributes;

            return final_output;
        } catch (Exception e) {
            System.err.println("Error while converting text to JSON: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
