package com.yfshi.class012;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class SpellChecker {
    @SuppressWarnings("rawtypes")
    private List wordsList;

    @SuppressWarnings("rawtypes")
    private Set wordsSet;

    @SuppressWarnings("rawtypes")
    private Map wordsMap;

    Properties wordsProp;

    public SpellChecker() {
        System.out.println("Inside SpellChecker constructor.");
    }
    public String checkSpelling(String text) {
        String result = "success";
        String errWords = "";
        if (text == null || text.trim().length() < 1
                || wordsList == null || wordsList.size() < 1) {
            return result;
        }
        String[] words = text.trim().split(",");
        for(String word : words) {
            if (word != null && word.trim().length() > 0) {
                if(! wordsList.contains(word.trim().toLowerCase())) {
                    errWords += "".equals(errWords) ? word.trim() : "," + word.trim();
                }
            }
        }
        return errWords.length() < 1 ? result : errWords + " spells error!";
    }
    @SuppressWarnings("rawtypes")
    public void setWordsList(List wordsList) {
        System.out.println("set SpellChecker wordsList :" + wordsList);
        this.wordsList = wordsList;
    }
    @SuppressWarnings("rawtypes")
    public List getWordsList() {

        return wordsList;
    }

    @SuppressWarnings("rawtypes")
    public void setWordsSet(Set wordsSet) {
        System.out.println("set SpellChecker wordsSet :" + wordsSet);
        this.wordsSet = wordsSet;
    }
    @SuppressWarnings("rawtypes")
    public Set getWordsSet() {

        return wordsSet;
    }

    @SuppressWarnings("rawtypes")
    public void setWordsMap(Map wordsMap) {
        System.out.println("set SpellChecker wordsMap :" + wordsMap);
        this.wordsMap = wordsMap;
    }
    @SuppressWarnings("rawtypes")
    public Map getWordsMap() {

        return wordsMap;
    }

    public void setWordsProp(Properties wordsProp) {
        System.out.println("set SpellChecker wordsProp :" + wordsProp);
        this.wordsProp = wordsProp;
    }
    public Properties getWordsProp() {

        return wordsProp;
    }
}
