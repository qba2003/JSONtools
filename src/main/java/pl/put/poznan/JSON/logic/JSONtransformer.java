package pl.put.poznan.JSON.logic;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.JSON.logic.decorators.*;

public class JSONtransformer {

    public String method;
    public String attributes;

    public JSONtransformer(String method, String attributes) {
        this.method = method;
        this.attributes = attributes;
    }

    public String transform(JSON data) {
        ObjectMapper mapper = new ObjectMapper();
        try {

            String result = "";
            if(method.equals("full")) {

                fullDecorator full_dec= new fullDecorator(data);
                result = full_dec.getData();
            }
            else if(method.equals("minify")) {
                minifyDecorator min_dec = new minifyDecorator(data);
                result = min_dec.getData();
            }
            else if(method.equals("delete")) {
                removeDecorator del_dec = new removeDecorator(data);
                del_dec.setAttributes(attributes);

                result = del_dec.getDataDeleted()[0];
            }
            else if(method.equals("select")) {
                selectedDecorator sel_dec = new selectedDecorator(data);
                sel_dec.setAttributes(attributes);

                result =  sel_dec.getDataSelected()[0];
            }
            else if(method.equals("compare")) {
                JsonNode entry_json = mapper.readTree(data.getData());
                String main_json = mapper.writeValueAsString(entry_json.get("main"));
                String sec_json = mapper.writeValueAsString(entry_json.get("second"));
                JSON main = new JSONimpl(main_json);

                compareDecorator comp_dec = new compareDecorator(main);
                comp_dec.setAttributes(sec_json);

                String entry_lines = comp_dec.getDataComparison()[2];
                String[] splitted = entry_lines.split("\n");


                for(int i = 0; i<splitted.length;i++) {
                    if(splitted[i].trim().equals("Different")) {
                        result = result + i + ',';
                    }
                }
                if(!result.isEmpty()) {
                    result = result.substring(0, result.length() - 1);
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
