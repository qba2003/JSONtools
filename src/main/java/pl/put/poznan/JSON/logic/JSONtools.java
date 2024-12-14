package pl.put.poznan.JSON.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class JSONtools {

    private final String[] transforms;

    public JSONtools(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){
        // of course, normally it would do something based on the transforms
        return text.toUpperCase();
    }
}
