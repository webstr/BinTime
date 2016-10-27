package com.ponikarchuk.controller;

import com.ponikarchuk.service.WordFrequencyServiceImpl;
import com.ponikarchuk.model.WordFrequency;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
public class CustomRestController {
    public static Map<String, Integer> map = new HashMap<>();


    @RequestMapping("/second")
    public List<WordFrequency> second() {
        return new WordFrequencyServiceImpl().listWordFrequency();
    }

    @RequestMapping(value = "/first", method = RequestMethod.POST)
    public void first(@RequestParam("files") List<MultipartFile> files) {
        new WordFrequencyServiceImpl().addViewWordFrequency(map, files);
        map = new HashMap<>();
    }
}
