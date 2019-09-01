package com.yfshi.class012;

import com.yfshi.class010.Bean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class TextEditorTest {


    @Test
    public void test(){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_autowire.xml");

        TextEditor textEditor = context.getBean("textEditor", TextEditor.class);
        System.out.println("textEditor->generateType: " + textEditor.getGenerateType());
        System.out.println("textEditor->checkText: " + textEditor.getText());
//        System.out.println("textEditor->spellCheck: " + textEditor.spellCheck());
        System.out.println("");

//        TextEditor textEditor2 = context.getBean("textEditor2", TextEditor.class);
//        System.out.println("textEditor2->generateType: " + textEditor2.getGenerateType());
//        System.out.println("textEditor2->checkText: " + textEditor2.getText());
//        System.out.println("textEditor2->spellCheck: " + textEditor2.spellCheck());
//        System.out.println("");
//
//        TextEditor textEditor3 = context.getBean("textEditor3", TextEditor.class);
//        System.out.println("textEditor3->generateType: " + textEditor3.getGenerateType());
//        System.out.println("textEditor3->checkText: " + textEditor3.getText());
//        System.out.println("textEditor3->spellCheck: " + textEditor3.spellCheck());

        context.close();



    }

}