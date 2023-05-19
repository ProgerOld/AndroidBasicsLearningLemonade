package com.example.androidbasicslearninglemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidbasicslearninglemonade.ui.theme.AndroidBasicsLearningLemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBasicsLearningLemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@Composable
fun LemonApp(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    val imageResource = when(currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textResource = when(currentStep){
        1 -> R.string.lemon_select
        2 -> R.string.lemon_squeeze
        3 -> R.string.lemon_drink
        else -> R.string.lemon_empty_glass
    }

    val contentDescription = when(currentStep){
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }

    when (currentStep) {
        1 -> {
            LemonTextAndImage(
                textLabelResource = textResource,
                contentDescription = contentDescription,
                imageResource = imageResource,
                onImageClick = {
                    currentStep = 2
                    squeezeCount = (2..4).random()
                }
            )
        }

        2 -> {
            LemonTextAndImage(
                textLabelResource = textResource,
                contentDescription = contentDescription,
                imageResource = imageResource,
                onImageClick = {
                    squeezeCount--
                    if (squeezeCount == 0) {
                        currentStep = 3
                    }
                }
            )
        }


        3 -> {
            LemonTextAndImage(
                textLabelResource = textResource,
                contentDescription = contentDescription,
                imageResource = imageResource,
                onImageClick = {
                    currentStep = 4
                }
            )
        }
        4 -> {
            LemonTextAndImage(
                textLabelResource = textResource,
                contentDescription = contentDescription,
                imageResource = imageResource,
                onImageClick = {
                    currentStep = 1
                }
            )
        }

    }

}

@Composable
fun LemonTextAndImage(textLabelResource: Int,
                      contentDescription: Int,
                      imageResource: Int,
                      onImageClick: () -> Unit,
                      modifier: Modifier = Modifier) {
    Box(modifier = modifier){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = stringResource(contentDescription),
                    modifier = Modifier
                        .width(250.dp)
                        .height(250.dp)
                        .padding(10.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(textLabelResource),
                style = MaterialTheme.typography.bodyLarge)

        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidBasicsLearningLemonadeTheme {
        LemonApp()
    }
}