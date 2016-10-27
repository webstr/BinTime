package com.ponikarchuk.service;

import com.ponikarchuk.dao.WordFrequencyDAO;
import com.ponikarchuk.dao.WordFrequencyDaoImpl;
import com.ponikarchuk.model.WordFrequency;
import com.ponikarchuk.thread.Parser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

@Service
public class WordFrequencyServiceImpl implements WordFrequencyService{

    private WordFrequencyDAO wordFrequencyDAO = new WordFrequencyDaoImpl();

    @Transactional
    public void addWordFrequency(WordFrequency wordFrequency) {
        wordFrequencyDAO.addWordFrequency(wordFrequency);
    }

    @Transactional
    public List listWordFrequency() {
        return wordFrequencyDAO.listWordFrequency();
    }

    @Transactional
    public void addViewWordFrequency(Map<String, Integer> map, List<MultipartFile> files) {
        wordFrequencyDAO.deleteAll();

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

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            WordFrequency wordFrequency = new WordFrequency();
            wordFrequency.setValue(entry.getKey());
            wordFrequency.setCount(entry.getValue());
            wordFrequencyDAO.addWordFrequency(wordFrequency);
        }

    }
}
