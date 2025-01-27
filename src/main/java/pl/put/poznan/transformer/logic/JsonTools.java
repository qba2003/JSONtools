package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class JsonTools {

    private final String[] transforms;

    public JsonTools(String[] transforms){
        this.transforms = transforms;
    }


}
