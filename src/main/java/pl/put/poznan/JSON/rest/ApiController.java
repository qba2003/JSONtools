package pl.put.poznan.JSON.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.JSON.logic.Json;
import pl.put.poznan.JSON.logic.JsonImpl;
import pl.put.poznan.JSON.logic.JsonTransformer;

@RestController
public class ApiController {

    private final Logger logger = LoggerFactory.getLogger(JsonHome.class);
    /**
     * Obsługuje żądanie POST na ścieżkę `/request`.
     * Umożliwia przetwarzanie danych JSON na podstawie wybranej metody oraz dodatkowych atrybutów.
     *
     * @param method     Określa metodę przetwarzania JSON. Domyślna wartość to "full".
     *                   Dostępne opcje to:
     *                      - full: pełne przetwarzanie JSON,
     *                      - minify: minimalizacja JSON,
     *                      - delete: usuwanie atrybutów JSON,
     *                      - select: wybór określonych atrybutów JSON,
     *                      - compare: porównywanie dwóch JSON-ów.
     * @param attributes Lista atrybutów używanych w metodach `delete`, `select` lub `compare`.
     *                   Przekazywana jako ciąg znaków. Domyślnie jest pusty.
     * @param data       JSON wejściowy przekazywany w ciele żądania HTTP.
     * @return Przekształcony JSON jako ciąg znaków.
     */

    @RequestMapping(method = RequestMethod.POST,value = "/request")
    public String postAPI(@RequestParam(name = "method", defaultValue = "full") String method,
                                          @RequestParam(name = "attributes", defaultValue = "") String attributes,
                                          @RequestBody String data) {
        logger.info("Request for " + method);
        if(!attributes.equals("")) {
            logger.debug("Attributes: " + attributes);
        }
        try {
            String data2=  data.substring(1, data.length() - 1);;
            Json json = new JsonImpl(data2);
            JsonTransformer transformer = new JsonTransformer(method, attributes);

            System.out.println(transformer.transform(json));
            return transformer.transform(json);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
