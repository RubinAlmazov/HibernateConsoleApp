package com.hibernate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.hibernate");
        Console console = context.getBean(Console.class);
        console.starter();
    }
 }
