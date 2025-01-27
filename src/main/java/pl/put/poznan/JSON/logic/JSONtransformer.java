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

            StringBuilder result = new StringBuilder();
            switch (method) {
                case "full":

                    fullDecorator full_dec = new fullDecorator(data);
                    result = new StringBuilder(full_dec.getData());
                    break;
                case "minify":
                    minifyDecorator min_dec = new minifyDecorator(data);
                    result = new StringBuilder(min_dec.getData());
                    break;
                case "delete":
                    removeDecorator del_dec = new removeDecorator(data);
                    del_dec.setAttributes(attributes);

                    result = new StringBuilder(del_dec.getDataDeleted()[0]);
                    break;
                case "select":
                    selectedDecorator sel_dec = new selectedDecorator(data);
                    sel_dec.setAttributes(attributes);

                    result = new StringBuilder(sel_dec.getDataSelected()[0]);
                    break;
                case "compare":
                    JsonNode entry_json = mapper.readTree(data.getData());
                    String main_json = mapper.writeValueAsString(entry_json.get("main"));
                    String sec_json = mapper.writeValueAsString(entry_json.get("second"));
                    JSON main = new JSONimpl(main_json);

                    compareDecorator comp_dec = new compareDecorator(main);
                    comp_dec.setAttributes(sec_json);

                    String entry_lines = comp_dec.getDataComparison()[2];
                    String[] splitted = entry_lines.split("\n");


                    for (int i = 0; i < splitted.length; i++) {
                        if (splitted[i].trim().equals("Different")) {
                            result.append(i).append(',');
                        }
                    }
                    if (result.length() > 0) {
                        result = new StringBuilder(result.substring(0, result.length() - 1));
                    }
                    break;
            }
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
