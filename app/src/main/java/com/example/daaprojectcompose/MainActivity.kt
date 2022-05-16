package com.example.daaprojectcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.daaprojectcompose.ui.theme.DAAprojectcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DAAprojectcomposeTheme {
                val inputValue = remember {
                    mutableStateOf("")
                }
                val viewModel : ViewModel = ViewModel()
                val sortedArray by viewModel._array.observeAsState()
                val listValue = remember {
                    mutableListOf<String>()
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .height(
                                LocalConfiguration.current.screenWidthDp.dp / 8
                            )
                            .width(LocalConfiguration.current.screenHeightDp.dp / 8)
                    )
                    Spacer(modifier = Modifier.padding(LocalConfiguration.current.screenHeightDp.dp/45))
                    Text(text = "Enter your numbers that needs to be sorted")
                    Spacer(modifier = Modifier.padding(LocalConfiguration.current.screenHeightDp.dp/45))
                    OutlinedTextField(value = inputValue.value, onValueChange = {inputValue.value = it}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
                    Spacer(modifier = Modifier.padding(LocalConfiguration.current.screenHeightDp.dp/45))
                    Row(
                        modifier = Modifier
                            .width(LocalConfiguration.current.screenWidthDp.dp - 40.dp)
                            .height(
                                LocalConfiguration.current.screenHeightDp.dp / 30
                            )
                    ) {
                        sortedArray?.forEach { it->
                            element(element = it)
                            Spacer(modifier = Modifier.padding(LocalConfiguration.current.screenWidthDp.dp/30))
                        }
                    }
                    Button(onClick = {
                        val li = inputValue.value.split(" ".toRegex())
                        li.forEachIndexed{ i, s ->
                            listValue.add(i,s)
                        }
                        viewModel.shellSort(listValue)
                        Toast.makeText(this@MainActivity,"${sortedArray?.toString()}", Toast.LENGTH_SHORT).show()
                    }) {
                        Text(text = "SHELL SORT")
                    }
                }
            }
        }
    }
}
@Composable
fun element(
    element:String
) {
    Box(modifier = Modifier
        .size(30.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(Color.Blue), contentAlignment = Alignment.Center){
        Text(text = "$element", fontSize = 15.sp, style = TextStyle(color = Color.White))
    }
}