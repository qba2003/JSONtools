package pl.put.poznan.JSON.logic.decorators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.JSON.logic.JSON;

import java.util.Scanner;

public class compareDecorator extends JSONDecorator {
    
    private String attributes;
    public compareDecorator(JSON text){
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
            StringBuilder comparison_result = getStringBuilder(main_json_string, second_json_string);

            result[0]=main_json_string;
            result[1]=second_json_string;
            result[2]= comparison_result.toString();

            return result;
        } catch (Exception e) {
            System.err.println("Error while removing attributes: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private static StringBuilder getStringBuilder(String main_json_string, String second_json_string) {
        Scanner main_scanner = new Scanner(main_json_string);
        Scanner second_scanner = new Scanner(second_json_string);

        StringBuilder comparison_result = new StringBuilder();
        while (main_scanner.hasNextLine()) {
            String line = main_scanner.nextLine();
            if(second_scanner.hasNextLine()) {
                if(line.equals(second_scanner.nextLine())) {
                    comparison_result.append(" Same \n");
                } else {
                    comparison_result.append(" Different \n");
                }
            } else {
                comparison_result.append(" Different \n");
            }
        }
        return comparison_result;
    }
}
