package pl.put.poznan.JSON.logic;
import org.json.JSONObject;
/**
 * This is just an example to show that the logic should be outside the REST service. Should it?
 */
public class JSONtools {

    private final String[] JSONtext;

    public JSONtools(String[] JSONtext){
        this.JSONtext = JSONtext;
    }

    public String JSONup(String text){
        // of course, normally it would do something based on the transforms
        return text.toUpperCase();
    }

    public String prettyPrintJSON(String minifiedJson) {
        try {
            JSONObject json = new JSONObject(minifiedJson);
            return json.toString(4); // 4 is the number of spaces for indentation
        } catch (Exception e) {
            return "Invalid JSON format: " + e.getMessage();
        }
    }

    public String filterJSON(String json, String[] propertyNames) {
        try {
            JSONObject originalJson = new JSONObject(json);
            JSONObject simplifiedJson = new JSONObject();

            for (String property : propertyNames) {
                if (originalJson.has(property)) {
                    simplifiedJson.put(property, originalJson.get(property));
                }
            }

            return simplifiedJson.toString(4); // 4 for pretty-printing
        } catch (Exception e) {
            return "Invalid JSON format: " + e.getMessage();
        }
    }
}
