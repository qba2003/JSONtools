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
 * Filters a JSON string to include only the specified properties.
 * If a property from the input list does not exist in the JSON, a message
 * is printed to the console indicating the missing property.
 *
 * @param json the input JSON string to be processed.
 * @param propertyNames an array of strings representing the keys (properties)
 *                      to retain in the filtered JSON object.
 * @return a formatted (pretty-printed) JSON string containing only the specified properties.
 *         If none of the specified properties exist, an empty JSON object is returned.
 * @throws org.json.JSONException if the input string is not a valid JSON format.
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * String inputJson = "{\"name\":\"John\", \"age\":30}";
 * String[] properties = {"name", "city"};
 * JSONtools tools = new JSONtools(new String[]{});
 * String result = tools.filterJSON(inputJson, properties);
 * System.out.println(result);
 * }
 * </pre>
 * <p>Console output if properties are missing:</p>
 * <pre>
 * {@code
 * Property not found: city
 * }
 * </pre>
 */
    public String filterJSON(String json, String[] propertyNames) {
    try {
        JSONObject originalJson = new JSONObject(json);
        JSONObject simplifiedJson = new JSONObject();

        for (String property : propertyNames) {
            if (originalJson.has(property)) {
                simplifiedJson.put(property, originalJson.get(property));
            } else {
                System.out.println("Property not found: " + property);
            }
        }

        return simplifiedJson.toString(4); 
    } catch (Exception e) {
        return "Invalid JSON format: " + e.getMessage();
        }
    }
}
