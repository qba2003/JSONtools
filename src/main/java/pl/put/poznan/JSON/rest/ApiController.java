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
