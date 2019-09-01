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
    public TextEditor(SpellChecker spellChecker) {
        System.out.println("Inside TextEditor constructor(checker).");
        this.testspellChecker = spellChecker;
    }
    public TextEditor(SpellChecker spellChecker, String generateType) {
        System.out.println("Inside TextEditor constructor.");
        this.testspellChecker = spellChecker;
        this.generateType = generateType;
    }
    public TextEditor(SpellChecker spellChecker, String generateType, String text) {
        System.out.println("Inside TextEditor constructor(checker, generteType, text).");
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
    public void setaverygooda(SpellChecker testspellChecker) {
        System.out.println("wocaozhehaizhenshicuode1");
    }
    public void setaverygooda2(SpellChecker testspellChecker) {
        System.out.println("wocaozhehaizhenshicuode2");
    }
    public void setaverygooda3(SpellChecker testspellChecker) {
        System.out.println("wocaozhehaizhenshicuode3");
    }



    @SuppressWarnings("rawtypes")
    public List getKeyWords() {
        return this.keywords;
    }
    public String spellCheck() {
        return testspellChecker.checkSpelling("TextEditor");
    }


}
