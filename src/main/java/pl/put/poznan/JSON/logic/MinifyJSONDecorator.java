package pl.put.poznan.JSON.logic;

import org.json.JSONObject;

public class MinifyJSONDecorator extends BaseJSONTool{
    @Override
    public String process(String json) {
        try {
            JSONObject JSON = new JSONObject(json);
            return JSON.toString(4);
        } catch (Exception e) {
            return "Invalid JSON format: " + e.getMessage();
        }
    }
}
