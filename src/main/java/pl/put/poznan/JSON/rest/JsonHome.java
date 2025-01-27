package pl.put.poznan.JSON.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import pl.put.poznan.JSON.logic.Json;
import pl.put.poznan.JSON.logic.JsonImpl;
import pl.put.poznan.JSON.logic.decorators.deleteElementDecorator;
import pl.put.poznan.JSON.logic.decorators.comparisonDecorator;
import pl.put.poznan.JSON.logic.decorators.showSelectedDecorator;
import pl.put.poznan.JSON.logic.decorators.fullJsonDecorator;
import pl.put.poznan.JSON.logic.decorators.minifyDecorator;


@Controller
public class JsonHome {

    @RequestMapping("/")
    public String returnIndex() {
        return "home";
    }

    @PostMapping("/posting")
    public String post1(@RequestParam("input2") String finalInput, Model model) {
        String[] arrStr = {};
        Json json = new JsonImpl(finalInput);

        try {
            fullJsonDecorator fulljsondec = new fullJsonDecorator(json);
            String jsonfull = fulljsondec.getData();
            System.out.println(jsonfull);
            model.addAttribute("input2", jsonfull);
        } catch (Exception e) {
            model.addAttribute("input2", "Invalid JSON input!");
        }
        return "resultFull";
    }

    @PostMapping("/postingMinify")
    public String post2(@RequestParam("input1") String finalInput, Model model) {
        String[] arrStr = {};
        Json json = new JsonImpl(finalInput);

        try {
            minifyDecorator mindec = new minifyDecorator(json);
            String jsonminify = mindec.getData();
            model.addAttribute("input1", jsonminify);
        } catch (Exception e) {
            model.addAttribute("input1", "Invalid JSON input: " + e.getMessage());
        }
        return "resultMinify";
    }

    @PostMapping("/postingSelected")
    public String post3(@RequestParam("SelectedJSON") String finalInput,
                        @RequestParam("SelectedAttributes") String attributes, Model model) {
        String[] arrStr = {};

        Json json = new JsonImpl(finalInput);

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

    @PostMapping("/postingDeleted")
    public String post4(@RequestParam("DeletedJSON") String finalInput,
                        @RequestParam("DeleteAttributes") String attributes, Model model) {

        Json json = new JsonImpl((finalInput));

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


    @PostMapping("/postingComparison")
    public String post5(@RequestParam("MainJSON") String mainInput, @RequestParam("SecJSON") String secInput, Model model) {
        String[] arrStr = {};
        Json json = new JsonImpl(mainInput);
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
