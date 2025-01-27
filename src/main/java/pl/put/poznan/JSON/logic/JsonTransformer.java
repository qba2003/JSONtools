package pl.put.poznan.JSON.logic;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.JSON.logic.decorators.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonTransformer {

    public String method;
    public String attributes;
    private final Logger logger = LoggerFactory.getLogger(JsonTransformer.class);

    public JsonTransformer(String method, String attributes) {
        this.method = method;
        this.attributes = attributes;
    }

    public String transform(Json data) {
        ObjectMapper mapper = new ObjectMapper();
        try {

            String result = "";
            if(method.equals("full")) {

                fullJsonDecorator full_dec= new fullJsonDecorator(data);
                result = full_dec.getData();
            }
            else if(method.equals("minify")) {
                minifyDecorator min_dec = new minifyDecorator(data);
                result = min_dec.getData();
            }
            else if(method.equals("delete")) {
                deleteElementDecorator del_dec = new deleteElementDecorator(data);
                del_dec.setAttributes(attributes);

                result = del_dec.getDataDeleted()[0];
            }
            else if(method.equals("select")) {
                showSelectedDecorator sel_dec = new showSelectedDecorator(data);
                sel_dec.setAttributes(attributes);

                result =  sel_dec.getDataSelected()[0];
            }
            else if(method.equals("compare")) {
                JsonNode entry_json = mapper.readTree(data.getData());
                String main_json = mapper.writeValueAsString(entry_json.get("main"));
                String sec_json = mapper.writeValueAsString(entry_json.get("second"));
                Json main = new JsonImpl(main_json);

                comparisonDecorator comp_dec = new comparisonDecorator(main);
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
            } else {
                logger.debug("Requested method dosen't exist");
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
