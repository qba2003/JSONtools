package pl.put.poznan.JSON.logic.decorators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.JSON.logic.JSON;

public class selectedDecorator extends JSONDecorator {

    private String attributes;
    public selectedDecorator(JSON text) {
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
        StringBuilder left_attributes = new StringBuilder();
        StringBuilder jsonText = new StringBuilder("{");
        StringBuilder fianl_atributes = new StringBuilder("Wybrane atrubyty: ");


        try {
            JsonNode json = mapper.readTree(json_text);
            for (String s : splitted) {
                JsonNode single_node = json.get(s.trim());
                if (single_node != null) {
                    fianl_atributes.append(s.trim()).append(',');
                    jsonText.append('"').append(s.trim()).append('"').append(" : ").append(mapper.writeValueAsString(single_node)).append(",");
                } else {
                    left_attributes.append(s.trim()).append(", ");
                }

            }

            if(jsonText.toString().equals("{")) {
                fianl_atributes = new StringBuilder("Żaden z podanych argumentów nie znajduje się w dnaych");
                jsonText = new StringBuilder();
            } else {
                jsonText = new StringBuilder(jsonText.substring(0, jsonText.length() - 1) + '}');

                fianl_atributes = new StringBuilder(fianl_atributes.substring(0, fianl_atributes.length() - 1));

                if(left_attributes.length() > 0) {
                    left_attributes = new StringBuilder(left_attributes.substring(0, left_attributes.length() - 2));

                    fianl_atributes.append(". Źle podano atrybuty: ").append(left_attributes).append('.');
                }
            }
            JsonNode final_json = mapper.readTree(jsonText.toString());

            final_output[0] = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(final_json);
            final_output[1] = fianl_atributes.toString();

            return final_output;
        } catch (Exception e) {
            System.err.println("Error while converting text to JSON: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
