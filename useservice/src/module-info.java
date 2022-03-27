module useservice {
    requires com.horstmann.greetsvc;
    uses com.horstmann.greetsvc.GreeterService; // 消费模块必须指定服务加载器(多个类实现的同一个接口)
}