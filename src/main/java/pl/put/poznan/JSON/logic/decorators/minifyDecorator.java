package pl.put.poznan.JSON.logic.decorators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.JSON.logic.JSON;

/**
 * A decorator class that minifies JSON strings.
 * <p>
 * This class extends the {@link JSONDecorator} and modifies the behavior
 * of the base {@link JSON} object by returning a minified version of the JSON data.
 * Minifying involves removing unnecessary whitespace, line breaks, and indentation
 * from the JSON string.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>
 * JSON json = new JSONimpl("{\n  \"key\": \"value\"\n}");
 * JSON minifiedJson = new minifyDecorator(json);
 * System.out.println(minifiedJson.getData()); // Output: {"key":"value"}
 * </pre>
 *
 * @see JSONDecorator
 * @see JSON
 */
public class minifyDecorator extends JSONDecorator {

    /**
     * Constructs a {@code minifyDecorator} object that wraps a given {@link JSON} object.
     *
     * @param text the {@link JSON} object to be decorated
     */
    public minifyDecorator(JSON text) {
        super(text);
    }

    /**
     * Returns the minified version of the JSON data.
     * <p>
     * This method retrieves the JSON string from the base {@link JSON} object,
     * processes it to remove unnecessary whitespace, and returns the minified result.
     * </p>
     *
     * @return the minified JSON string
     */
    @Override
    public String getData() {
        return minify(super.getData());
    }

    /**
     * Minifies the provided JSON string.
     * <p>
     * This method parses the input string into a JSON tree using Jackson's {@link ObjectMapper},
     * and then writes it back as a compact JSON string without unnecessary whitespace.
     * </p>
     *
     * @param text the JSON string to be minified
     * @return the minified JSON string
     * @throws RuntimeException if the input text is not valid JSON
     */
    public String minify(String text) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode main_json_tree = mapper.readTree(text);
            return mapper.writeValueAsString(main_json_tree);
        } catch (Exception e) {
            System.err.println("Error converting text to JSON: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
