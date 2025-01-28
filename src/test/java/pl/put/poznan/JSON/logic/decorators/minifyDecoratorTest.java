package pl.put.poznan.JSON.logic.decorators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.put.poznan.JSON.logic.JSON;
import pl.put.poznan.JSON.logic.JSONimpl;

public class minifyDecoratorTest {

    @Test
    public void testMinifyValidJson() {
        JSON json = new JSONimpl("{\n  \"key\": \"value\"  \n}");
        JSON minifiedJson = new minifyDecorator(json);

        String result = minifiedJson.getData();
        assertEquals("{\"key\":\"value\"}", result);
    }


    @Test
    public void testMinifyAlreadyMinifiedJson() {
        JSON json = new JSONimpl("{\"key\":\"value\"}");
        JSON minifiedJson = new minifyDecorator(json);

        String result = minifiedJson.getData();
        assertEquals("{\"key\":\"value\"}", result);
    }


    @Test
    public void testMinifyInvalidJson() {
        JSON json = new JSONimpl("{\"key\": value}");
        JSON minifiedJson = new minifyDecorator(json);

        assertThrows(RuntimeException.class, minifiedJson::getData);
    }



    @Test
    public void testMinifyNestedJson() {
        JSON json = new JSONimpl("{\n  \"key\": {\n    \"nestedKey\": \"nestedValue\"\n  }\n}");
        JSON minifiedJson = new minifyDecorator(json);

        String result = minifiedJson.getData();
        assertEquals("{\"key\":{\"nestedKey\":\"nestedValue\"}}", result);
    }


    @Test
    public void testMinifyEmptyJson() {
        JSON json = new JSONimpl("{}");
        JSON minifiedJson = new minifyDecorator(json);

        String result = minifiedJson.getData();
        assertEquals("{}", result);
    }
}
