package classpath.test

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview


infix fun Context.string(id: Int) = getString(id)

class MainActivity : ComponentActivity()

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting("Android")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewWithAppName() {
    Greeting(LocalContext.current string R.string.app_name)
}



