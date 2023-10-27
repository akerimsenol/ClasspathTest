package classpath.test.lib

// lib -> api(lib1) -> api(lib2) -> impl(lib3)
val id1 = classpath.test.module1.R.string.module1
val id2 = classpath.test.module2.R.string.module2
// with compile module3 doesn't resolve, and doesn't show up in resource editors
// with runtime module3 R doesn't resolve in code, but resources resolve in resource editor
//val id3 = classpath.test.module3.R.string.module3