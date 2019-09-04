package com.yfshi.class012;

import java.util.List;

public class TextEditor {
    private SpellChecker testspellChecker;
    private String generateType;
    private String text;

    @SuppressWarnings("rawtypes")
    private List keywords;

    public TextEditor() {
        System.out.println("default no arg TextEditor constructor().");
    }
    @SuppressWarnings("rawtypes")
    public TextEditor(List list) {
        System.out.println("Inside TextEditor constructor(list).");
        this.keywords = list;
    }

    public TextEditor(String text,String generateType) {
        System.out.println("Inside TextEditor constructor(text,generateType).");
        this.text = text;
        this.generateType = generateType;
    }

    public TextEditor(  String text,String generateType,SpellChecker spellChecker) {
        System.out.println("Inside TextEditor constructor(text, generateType, spellChecker).");
        this.testspellChecker = spellChecker;
        this.generateType = generateType;
        this.text = text;
    }

    public String getText() {

        return this.text;
    }
    public void setText(String text) {
        System.out.println("set TextEditor Text: " + text);
        this.text = text;
    }
    public String getGenerateType() {

        return this.generateType;
    }
    public void setGenerateType( String generateType) {
        System.out.println("set TextEditor generate type: " + generateType);
        this.generateType = generateType;
    }

    public SpellChecker getspellChecker() {
        return testspellChecker;
    }
    public void setspellChecker(SpellChecker testspellChecker) {
        System.out.println("set TextEditor spellChecker : " + testspellChecker);
        this.testspellChecker = testspellChecker;
    }

    @SuppressWarnings("rawtypes")
    public List getKeyWords() {
        return this.keywords;
    }
    public String spellCheck() {
        return testspellChecker.checkSpelling("TextEditor");
    }


}
