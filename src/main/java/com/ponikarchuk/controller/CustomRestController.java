package com.ponikarchuk.controller;

import com.ponikarchuk.thread.Parser;
import com.ponikarchuk.dao.WordFrequencyDaoImpl;
import com.ponikarchuk.model.WordFrequency;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Semaphore;

@RestController
public class CustomRestController {
    public static Map<String, Integer> map = new HashMap<>();


    @RequestMapping("/second")
    public List<WordFrequency> second() {
        return new WordFrequencyDaoImpl().listWordFrequency();
    }

    @RequestMapping(value = "/first", method = RequestMethod.POST)
    public void first(@RequestParam("files") List<MultipartFile> files) {
        WordFrequencyDaoImpl wordFrequencyDao = new WordFrequencyDaoImpl();
        wordFrequencyDao.deleteAll();

        Semaphore semaphore = new Semaphore(3);
        List<Thread> threadList = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            try {
                Scanner in = new Scanner(multipartFile.getInputStream());
                Parser parser = new Parser(semaphore, in);
                threadList.add(parser);
                parser.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            WordFrequency wordFrequency = new WordFrequency();
            wordFrequency.setValue(entry.getKey());
            wordFrequency.setCount(entry.getValue());
            wordFrequencyDao.addWordFrequency(wordFrequency);
        }
    }
}
