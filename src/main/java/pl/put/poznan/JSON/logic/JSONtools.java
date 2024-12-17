package pl.put.poznan.JSON.logic;
import org.json.JSONObject;
/**
 * The {@code JSONtools} class provides utility methods for JSON processing,
 * such as transforming JSON text to uppercase, pretty-printing JSON,
 * and filtering JSON objects based on specific properties.
 */
public class JSONtools {
    /** An array of JSON text inputs provided to the class. */
    private final String[] JSONtext;

    /**
     * Constructs a {@code JSONtools} instance with the specified JSON text.
     *
     * @param JSONtext an array of strings representing JSON text inputs.
     */
    public JSONtools(String[] JSONtext){
        this.JSONtext = JSONtext;
    }

    
    /**
     * Converts the provided text to uppercase.
     *
     * @param text the input string to be transformed.
     * @return the uppercase version of the input string.
     */
    public String JSONup(String text){
        // of course, normally it would do something based on the transforms
        return text.toUpperCase();
    }

    /**
     * Formats a minified JSON string into a pretty-printed JSON string with indentation.
     *
     * @param minifiedJson the input JSON string to be formatted.
     * @return a pretty-printed JSON string with 4-space indentation, or an error message
     *         if the input is not a valid JSON format.
     */
    public String prettyPrintJSON(String minifiedJson) {
        try {
            JSONObject json = new JSONObject(minifiedJson);
            return json.toString(4); // 4 is the number of spaces for indentation
        } catch (Exception e) {
            return "Invalid JSON format: " + e.getMessage();
        }
    }

    /**
     * Filters a JSON object to retain only the specified properties.
     *
     * @param json          the input JSON string to be filtered.
     * @param propertyNames an array of property names to retain in the JSON object.
     * @return a pretty-printed JSON string containing only the specified properties,
     *         or an error message if the input is not a valid JSON format.
     */
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
