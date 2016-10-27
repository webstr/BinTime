package com.ponikarchuk.service;

import com.ponikarchuk.dao.WordFrequencyDAO;
import com.ponikarchuk.model.WordFrequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WordFrequencyServiceImpl implements WordFrequencyService{

    @Autowired
    private WordFrequencyDAO wordFrequencyDAO;

    @Transactional
    public void addWordFrequency(WordFrequency wordFrequency) {
        wordFrequencyDAO.addWordFrequency(wordFrequency);
    }

    @Transactional
    public List listWordFrequency() {
        return wordFrequencyDAO.listWordFrequency();
    }
}
