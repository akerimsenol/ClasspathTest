package classpath.test.lib

import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

class AppActivity : ComponentActivity()

@Composable
fun Greeting(resourcePath: String, ids: List<Int>, comment : String = "") {
    val context = LocalContext.current
    Text(
        text = if (ids.isEmpty()) """
            $resourcePath
            $comment
            """.trimIndent()
        else ids.joinToString("\n") { """
            $resourcePath
            id=$it${if (it != 0) " value = ${context.getString(it)}" else ""}
             """.trimIndent() + if (comment.isNotEmpty()) "\n$comment" else ""},
        modifier = Modifier
    )
}

@Preview
@Composable
fun Render() {
    Greeting("", listOf(
        classpath.test.module1.R.string.module1,
        classpath.test.module1.R.string.module1_2,
        classpath.test.module1.R.string.module1_3,
        classpath.test.module2.R.string.module2,
        classpath.test.module2.R.string.module2_3,
//        classpath.test.module3.R.string.module3,
    ))
}