package com.horstmann.greet;

import com.horstmann.greet.internal.GreeterImpl;

public interface Greeter {
    static Greeter newInstance() { return new GreeterImpl(); }
    String greet(String subject);
}