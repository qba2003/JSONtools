package pl.put.poznan.JSON.logic.decorators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.JSON.logic.Json;

import java.util.Scanner;

public class comparisonDecorator extends JsonDecorator{
    
    private String attributes;
    public comparisonDecorator(Json text){
        super(text);
    }
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
    
    public String[] getDataComparison(){
        return comparison(super.getData(),attributes);
    }
    
    

    public String [] comparison(String main_json, String json_to_compare) {
        ObjectMapper mapper = new ObjectMapper();
        String[] result = new String[3];
        try {
            JsonNode main_json_tree = mapper.readTree(main_json);
            JsonNode second_json_tree = mapper.readTree(json_to_compare);

            String main_json_string = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(main_json_tree);
            String second_json_string = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(second_json_tree);
            Scanner main_scanner = new Scanner(main_json_string);
            Scanner second_scanner = new Scanner(second_json_string);

            String comparison_result = "";
            while (main_scanner.hasNextLine()) {
                String line = main_scanner.nextLine();
                if(second_scanner.hasNextLine()) {
                    if(line.equals(second_scanner.nextLine())) {
                        comparison_result = comparison_result + " Same \n";
                    } else {
                        comparison_result = comparison_result + " Different \n";
                    }
                } else {
                    comparison_result = comparison_result + " Different \n";
                }
            }

            result[0]=main_json_string;
            result[1]=second_json_string;
            result[2]=comparison_result;

            return result;
        } catch (Exception e) {
            System.err.println("Error while removing attributes: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
