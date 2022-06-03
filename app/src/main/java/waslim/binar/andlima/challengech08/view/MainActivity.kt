package waslim.binar.andlima.challengech08.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.*
import dagger.hilt.android.AndroidEntryPoint
import waslim.binar.andlima.challengech08.R
import waslim.binar.andlima.challengech08.model.user.DataUserResponseItem
import waslim.binar.andlima.challengech08.network.ApiService
import waslim.binar.andlima.challengech08.view.theme.ChallengeCH08Theme
import waslim.binar.andlima.challengech08.viewmodel.ViewModelLogin

@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeCH08Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val mcontext = LocalContext.current

    Column( horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp, top = 30.dp)
                .fillMaxWidth()
                .fillMaxSize()){

        Text(text = "Login",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.padding(10.dp))

        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "iconregist")

        Spacer(modifier = Modifier.padding(40.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text(text = "Masukan Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(text = "Masukan Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") }
        )

        Spacer(modifier = Modifier.padding(150.dp))

        Button(onClick = {
            mcontext.startActivity(Intent(mcontext, HomeLayout::class.java))
        },
            modifier = Modifier
                .border(width = 2.dp, color = Color.Black)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Green)) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.padding(5.dp))

        Text(text = "Belum Punya Akun? Daftar Disini" ,
            textAlign = TextAlign.Center,
            color = Color.Black, modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    mcontext.startActivity(Intent(mcontext, RegisterLayout::class.java))
        })

    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ChallengeCH08Theme {
        Greeting()
    }
}