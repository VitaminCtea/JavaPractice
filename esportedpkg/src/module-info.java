module exportedpkg {
    // opens somePackage to ...和导出模块的exports xxx to ...效果一样。所以在导出的模块中限定了对某些模块的开发权限下，那么opens语句也不再需要！
    requires com.horstmann.greet;
}