package pl.put.poznan.JSON.logic;

/**
 * This is just an example to show that the logic should be outside the REST service. A teraz zmieniam coby zobaczyc czy dziala
 */
public class JSONtools {

    private final String[] JSONtext;

    public JSONtools(String[] JSONtext){
        this.JSONtext = JSONtext;
    }

    public String JSONup(String text){
        // of course, normally it would do something based on the transforms
        return text.toUpperCase();
    }
}
