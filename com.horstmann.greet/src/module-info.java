module com.horstmann.greet {
    // exports xxx to ....意思是导出某个包, 而导出的包只能在to后面指定模块名中使用！没有列出的模块则不能使用这个导出的包
    // 如果不加to进行限定的话，则所有的模块都可以进行导入这个导出的包
    // 如下exports com.horstmann.greet to exportedpkg就只能在exportedpkg模块中使用了
    exports com.horstmann.greet to exportedpkg;
}