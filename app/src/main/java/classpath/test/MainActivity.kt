package classpath.test

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity()

@Composable
fun Greeting(resourcePath: String, id: Int? =null, comment : String = "") {
    Text(
        text = if (id == null) """
            $resourcePath
            $comment
            """.trimIndent()
        else """
            $resourcePath
            id=$id${if (id != 0) " value = ${LocalContext.current.getString(id)}" else ""}
             """.trimIndent() + if (comment.isNotEmpty()) "\n$comment" else "",
        modifier = Modifier
    )
}

@Preview
@Composable
fun Implementation() {
    Greeting("classpath.implementation.R.string.implementation", classpath.implementation.R.string.implementation)
}

@Preview
@Composable
fun CompileOnly() {
    Greeting("classpath.compileOnly.R.string.compile_only",
        classpath.compileOnly.R.string.compile_only,
        comment = """
            Source editing -> Navigation works
            Design tools -> ID resolves to 0
            Build succeeds
            Emulator -> crash
            """.trimIndent()
    )
// Caused by: java.lang.ClassNotFoundException: Didn't find class "classpath.compileOnly.R$string"
// on path: DexPathList[[zip file "/data/app/classpath.test-E4k6uqdApymg4hSMP1ozxA==/base.apk"],
// nativeLibraryDirectories=[// /data/app/classpath.test-E4k6uqdApymg4hSMP1ozxA==/lib/x86, ...
}

// Keeping commented out to get continous updated from design tools.
//@Preview
//@Composable
//fun RuntimeOnly() {
//    Greeting("classpath.runtimeOnly.R.string.runtime_only",
//        classpath.runtimeOnly.R.string.runtime_only,
//        comment = """
//            Source editing -> Doesn't resolve
//            Design tools -> Render problem (can be still run through run configuration)
//            Build succeeds
//            Emulator -> RuntimeOnly -> CORRECT
//            """.trimIndent()
//    )
//}

// Testing with conflicts, though it doesn't make much sense when the ids are non transitive
@Preview
@Composable
fun ConflictImplementation() {
    Greeting(
        "classpath.implementation.R.string.conflict",
        classpath.implementation.R.string.conflict,
        comment = """
            Source editing AND
            Design tools -> ConflictCompileOnly, why?
            Emulator -> ConflictImplementation -> CORRECT
            Build succeeds
            Order of dependencies doesn't affect the outcome
            """.trimIndent()
    )
}

@Preview
@Composable
fun ConflictCompileOnly() {
    Greeting(
        "classpath.compileOnly.R.string.conflict",
        classpath.compileOnly.R.string.conflict,
        comment = """
            Source editing -> Navigation works
            Design tools -> ID resolves to 0
            Build succeeds
            Emulator -> crash
            """.trimIndent() )
}

// Keeping commented out to get continous updated from design tools.
//
//@Preview
//@Composable
//fun ConflictRuntimeOnly() {
//    Greeting("classpath.runtimeOnly.R.string.conflict",
//        classpath.runtimeOnly.R.string.conflict,
//        comment = """
//            Even more funkiness.
//            Source editing -> Doesn't resolve
//            Design tools -> ConflictCompileOnly
//            Build succeeds
//            Emulator -> ConflictImplementation -> CORRECT
//            Order of dependencies doesn't affect the outcome
//            """.trimIndent()
//    )
//}
//
//
