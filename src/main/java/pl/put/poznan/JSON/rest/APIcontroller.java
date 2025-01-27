package pl.put.poznan.JSON.rest;

import org.springframework.web.bind.annotation.*;
import pl.put.poznan.JSON.logic.JSON;
import pl.put.poznan.JSON.logic.JSONimpl;
import pl.put.poznan.JSON.logic.JSONtransformer;

@RestController
public class APIcontroller {


    @RequestMapping(method = RequestMethod.POST,value = "/request")
    public String postAPI(@RequestParam(name = "method", defaultValue = "full") String method,
                                          @RequestParam(name = "attributes", defaultValue = "") String attributes,
                                          @RequestBody String data) {
        try {
            String data2=  data.substring(1, data.length() - 1);;
            JSON json = new JSONimpl(data2);
            JSONtransformer transformer = new JSONtransformer(method, attributes);

            System.out.println(transformer.transform(json));
            return transformer.transform(json);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
