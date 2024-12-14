package pl.put.poznan.JSON.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.JSON.logic.JSONtools;

import java.util.Arrays;


@RestController
@RequestMapping("/{text}")
public class JSONtoolsController {

    private static final Logger logger = LoggerFactory.getLogger(JSONtoolsController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                              @RequestParam(value="JSONtext", defaultValue="upper,escape") String[] JSONtext) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(JSONtext));

        // perform the transformation, you should run your logic here, below is just a silly example
        JSONtools JSON = new JSONtools(JSONtext);
        return JSON.JSONup(text);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String[] JSONtext) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(JSONtext));

        // perform the transformation, you should run your logic here, below is just a silly example
        JSONtools JSON = new JSONtools(JSONtext);
        return JSON.JSONup(text);
    }



}


