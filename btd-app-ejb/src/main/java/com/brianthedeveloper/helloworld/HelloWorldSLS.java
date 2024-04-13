package com.brianthedeveloper.helloworld;

import jakarta.ejb.Stateless;

@Stateless
public class HelloWorldSLS {

    public String sayHello() {
        return "Hello World2!";
    }
}
