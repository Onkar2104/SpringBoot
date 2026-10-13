package com.example.aop_demo.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class ApplicationPointcuts {

    @Pointcut ("within(com.example.aop_demo.controller..*)")
    public void controllerLayer() {

    }
}
