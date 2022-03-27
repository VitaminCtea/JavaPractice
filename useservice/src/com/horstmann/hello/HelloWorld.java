package com.horstmann.hello;

import com.horstmann.greetsvc.GreeterService;

import java.util.ServiceLoader;

public class HelloWorld {
    public static void main(String[] args) {
        String language = "de";

        // ServiceLoader作用是多个类实现了同一个接口，在这种情况下通过ServiceLoader可以找出所有实现这个接口的类，然后根据所需筛选出合适的类进行调用或操作
        // 与反射的getInterfaces()方法不同，getInterfaces()方法是找出一个类的所有接口，而ServiceLoader是找出同一个接口的所有类，正好相反！
        // 假设一个网站有不同的登录方式，例如有管理员登录、普通用户登录、VIP登录等等。那么就可以通过ServiceLoader来选择一个当前合适的登录方式
        // 具体做法是: 先从数据库取回用户的信息，从取回的信息字段判断是否是管理员、普通用户、VIP，然后根据这个字段来找出合适的类，最后完成登录！
        // 当然，实现的前提是这些实现登录的类必须使用一个公共的接口进行约束，否则ServiceLoader将无用武之地！最后再一一实现每个登录类的逻辑即可！
        // 注意：实现每个类时，必须使用无参构造器
        ServiceLoader<GreeterService> serviceLoader = ServiceLoader.load(GreeterService.class);
        GreeterService selectGreeterService = null;

        // Iterator接口循环等价于增强的for循环，即for(GreeterService greeterService: serviceLoader) { ... }
        for (GreeterService greeterService: serviceLoader) {
            if (greeterService.getLocale().getLanguage().equals(language)) {
                selectGreeterService = greeterService;
                break;
            }
        }

        if (selectGreeterService == null) System.out.println("No suitable greeter.");
        else System.out.println(selectGreeterService.greet("Modular World"));
    }
}