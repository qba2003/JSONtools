package pl.put.poznan.JSON.logic.decorators;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.put.poznan.JSON.logic.JSON;

import static org.junit.jupiter.api.Assertions.assertEquals;

class fullDecoratorTest {

    @Test
    void testFullDecoratorWithMock() {
        JSON mockJson = Mockito.mock(JSON.class);

        Mockito.when(mockJson.getData()).thenReturn("{\"name\":\"John\",\"age\":30}");

        fullDecorator fullDecorator = new fullDecorator(mockJson);

        String result = fullDecorator.getData();

        String expected = "{\n" +
                "  \"name\" : \"John\",\n" +
                "  \"age\" : 30\n" +
                "}";

        result = result.replace("\r\n", "\n");
        expected = expected.replace("\r\n", "\n");

        assertEquals(expected, result);

        Mockito.verify(mockJson).getData();
    }
}
