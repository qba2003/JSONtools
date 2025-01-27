package pl.put.poznan.transformer.rest;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import pl.put.poznan.transformer.logic.Json;
import pl.put.poznan.transformer.logic.JsonImpl;
import pl.put.poznan.transformer.logic.JsonTransformer;
import pl.put.poznan.transformer.logic.decorators.deleteElementDecorator;
import pl.put.poznan.transformer.logic.decorators.comparisonDecorator;
import pl.put.poznan.transformer.logic.decorators.showSelectedDecorator;
import pl.put.poznan.transformer.logic.decorators.fullJsonDecorator;
import pl.put.poznan.transformer.logic.decorators.minifyDecorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Kontroler obsługujący żądania HTTP związane z operacjami na danych JSON.
 * Oferuje funkcjonalności takie jak: pełne przetwarzanie JSON, minimalizacja, wybór,
 * usuwanie atrybutów oraz porównywanie dwóch JSON-ów.
 */
@Controller
public class JsonHome {
    private final Logger logger = LoggerFactory.getLogger(JsonHome.class);

    /**
     * Metoda obsługująca żądanie GET na ścieżkę główną.
     *
     * @return Nazwa widoku "home".
     */
    @RequestMapping("/")
    public String returnIndex() {
        return "home";
    }

    /**
     * Obsługuje żądanie POST do przetworzenia pełnego JSON.
     *
     * @param finalInput JSON wejściowy jako ciąg znaków.
     * @param model      Model do przekazywania danych do widoku.
     * @return Nazwa widoku "result".
     */
    @PostMapping("/posting")
    public String post1(@RequestParam("input2") String finalInput, Model model) {
        String[] arrStr = {};
        Json json = new JsonImpl(finalInput);
        logger.info("Form request for full json");

        try {
            fullJsonDecorator fulljsondec = new fullJsonDecorator(json);
            String jsonfull = fulljsondec.getData();
            System.out.println(jsonfull);
            model.addAttribute("input2", jsonfull);
        } catch (Exception e) {
            model.addAttribute("input2", "Invalid JSON input: " + e.getMessage());
        }
        return "result";
    }

    /**
     * Obsługuje żądanie POST do minimalizacji JSON.
     *
     * @param finalInput JSON wejściowy.
     * @param model      Model do przekazywania danych do widoku.
     * @return Nazwa widoku "resultMinify".
     */
    @PostMapping("/postingMinify")
    public String post2(@RequestParam("input1") String finalInput, Model model) {
        String[] arrStr = {};
        Json json = new JsonImpl(finalInput);
        logger.info("Form request for minify");

        try {
            minifyDecorator mindec = new minifyDecorator(json);
            String jsonminify = mindec.getData();
            model.addAttribute("input1", jsonminify);
        } catch (Exception e) {
            model.addAttribute("input1", "Invalid JSON input: " + e.getMessage());
        }
        return "resultMinify";
    }

    /**
     * Obsługuje żądanie POST do wybrania określonych atrybutów JSON.
     *
     * @param finalInput JSON wejściowy.
     * @param attributes Lista atrybutów do wybrania.
     * @param model      Model do przekazywania danych do widoku.
     * @return Nazwa widoku "resultSelected".
     */
    @PostMapping("/postingSelected")
    public String post3(@RequestParam("SelectedJSON") String finalInput,
                        @RequestParam("SelectedAttributes") String attributes, Model model) {
        String[] arrStr = {};

//        JsonTools tools = new JsonTools(arrStr);
        Json json = new JsonImpl(finalInput);
        logger.info("Form request for select");

        try {
            showSelectedDecorator show_selected = new showSelectedDecorator(json);
            show_selected.setAttributes(attributes);
            String[] selected_json = show_selected.getDataSelected();

            model.addAttribute("json", selected_json[0]);
            model.addAttribute("selected", selected_json[1]);
        } catch (Exception e) {
            model.addAttribute("json", "Invalid JSON input: " + e.getMessage());
        }
        return "resultSelected";
    }

    /**
     * Obsługuje żądanie POST do usuwania określonych atrybutów JSON.
     *
     * @param finalInput JSON wejściowy.
     * @param attributes Lista atrybutów do usunięcia.
     * @param model      Model do przekazywania danych do widoku.
     * @return Nazwa widoku "resultDeleted".
     */
    @PostMapping("/postingDeleted")
    public String post4(@RequestParam("DeletedJSON") String finalInput,
                        @RequestParam("DeleteAttributes") String attributes, Model model) {
        String[] arrStr = {};

        Json json = new JsonImpl((finalInput));

        logger.info("Form request for delete");

        try {
            deleteElementDecorator delete_elements = new deleteElementDecorator(json);
            delete_elements.setAttributes(attributes);
            String[] deleted_json = delete_elements.getDataDeleted();

            model.addAttribute("json", deleted_json[0]);
            model.addAttribute("deleted", deleted_json[1]);

        } catch (Exception e) {
            model.addAttribute("json", "Invalid JSON input: " + e.getMessage());
        }
        return "resultDeleted";
    }

    /**
     * Obsługuje żądanie POST do porównywania dwóch JSON-ów.
     *
     * @param mainInput JSON główny.
     * @param secInput  JSON do porównania.
     * @param model     Model do przekazywania danych do widoku.
     * @return Nazwa widoku "resultComparison".
     */
    @PostMapping("/postingComparison")
    public String post5(@RequestParam("MainJSON") String mainInput, @RequestParam("SecJSON") String secInput, Model model) {
        String[] arrStr = {};
        Json json = new JsonImpl(mainInput);
        logger.info("Form request for comparison");
        try {
            comparisonDecorator comparison = new comparisonDecorator(json);
            comparison.setAttributes(secInput);
            String[] json_arr = comparison.getDataComparison();

            model.addAttribute("main", json_arr[0]);
            model.addAttribute("sec", json_arr[1]);
            model.addAttribute("comp", json_arr[2]);
        } catch (Exception e) {
            model.addAttribute("json", "Invalid JSON input: " + e.getMessage());
        }
        return "resultComparison";
    }
}
