package com.ponikarchuk.dao;

import com.ponikarchuk.model.WordFrequency;

import java.util.List;

public interface WordFrequencyDAO {

    public void addWordFrequency(WordFrequency wordFrequency);

    public List listWordFrequency();

    public void deleteAll();
}
