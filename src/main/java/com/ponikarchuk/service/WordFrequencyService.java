package com.ponikarchuk.service;

import com.ponikarchuk.model.WordFrequency;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface WordFrequencyService {

    void addWordFrequency(WordFrequency wordFrequency);

    List listWordFrequency();

    void addViewWordFrequency(Map<String, Integer> map, List<MultipartFile> files);
}
