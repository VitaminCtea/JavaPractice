package com.horstmann.greetsvc.internal;

import com.horstmann.greetsvc.GreeterService;

import java.util.Map;

public class GermanGreeterFactory {
    // 如果实现一个类的工厂类，那么如果需要使用ServiceLoader的话，则需要在这个工厂类内实现静态的provider方法，以在这个静态方法中实例化某个类，provider返回值是服务加载器(接口)
    // 如果不需要工厂方法，则实现的类必须使用这个加载器(接口)进行约束，并且这个类是共有类型的！即: public class xxx implements interface {}
    // 同时还需要在导出模块的module-info.java中声明需要注入使用服务加载器(接口)的类列表，语法为: provides 服务加载器(接口) with 类列表，类列表其中使用逗号进行间隔！
    // 如果提供了工厂类，则不再需要把工厂内部实例化的类注入到列表中，只需把工厂类加入到注入列表中即可！服务加载器加载的时候会默认实例化工厂类的无参构造方法，并且自动调用工厂类中的provider静态方法！
    public static GreeterService provider() {
        return new GermanGreeter(Map.of("World", "Welt", "Modular World", "modulare Welt"));
    }
}