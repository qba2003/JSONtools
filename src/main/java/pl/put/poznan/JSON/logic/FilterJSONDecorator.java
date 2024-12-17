package pl.put.poznan.JSON.logic;

import org.json.JSONObject;

public class FilterJSONDecorator extends BaseJSONTool{
    private final String[] properties;

    public FilterJSONDecorator(String[] properties) {
        this.properties = properties;
    }

    @Override
    public String process(String json) {
        JSONObject originalJson = new JSONObject(json);
        JSONObject filteredJson = new JSONObject();

        for (String property : properties) {
            if (originalJson.has(property)) {
                filteredJson.put(property, originalJson.get(property));
            }
        }

        return filteredJson.toString(4); // return pretty-printed JSON
    }
}
