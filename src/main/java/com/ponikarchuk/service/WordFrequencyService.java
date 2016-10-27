package com.ponikarchuk.service;

import com.ponikarchuk.model.WordFrequency;

import java.util.List;

public interface WordFrequencyService {

    public void addWordFrequency(WordFrequency wordFrequency);

    public List listWordFrequency();
}
