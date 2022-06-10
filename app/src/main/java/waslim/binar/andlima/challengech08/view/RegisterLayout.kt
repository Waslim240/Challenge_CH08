package waslim.binar.andlima.challengech08.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import waslim.binar.andlima.challengech08.R
import waslim.binar.andlima.challengech08.view.theme.ChallengeCH08Theme
import waslim.binar.andlima.challengech08.viewmodel.ViewModelRegister

@AndroidEntryPoint
class RegisterLayout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeCH08Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Daftar()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Daftar() {
    val viewModelRegister = viewModel(modelClass = ViewModelRegister::class.java)
    val mcontext = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var konfpassword by remember { mutableStateOf("") }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp, top = 30.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .fillMaxSize()
            .height(100.dp)
    ){

        Text(
            text = "Register",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(15.dp))

        Image(
            modifier = Modifier
                .size(140.dp),
            painter = painterResource(id = R.drawable.img),
            contentDescription = "iconregist"
        )

        Spacer(modifier = Modifier.padding(40.dp))


        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            placeholder = { Text(text = "Masukan Username") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Username") }
        )

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

        OutlinedTextField(
            value = konfpassword,
            onValueChange = { konfpassword = it },
            placeholder = { Text(text = "Masukan Konfirmasi Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Konfirmasi Password") }
        )

        Spacer(modifier = Modifier.padding(60.dp))

        Button(
            onClick = {
            when {
                username.isEmpty() -> {
                    Toast.makeText(mcontext, "Username Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                }
                email.isEmpty() -> {
                    Toast.makeText(mcontext, "Email Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(mcontext, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                }
                konfpassword.isEmpty() -> {
                    Toast.makeText(mcontext, "Konfirmasi Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                }
                konfpassword != password -> {
                    Toast.makeText(mcontext, "Konfirmasi Password & Password Harus Sama", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(mcontext, "Registrasi Sukses", Toast.LENGTH_SHORT).show()
                    viewModelRegister.register(email, password, username)
                    mcontext.startActivity(Intent(mcontext, MainActivity::class.java))
                    activity?.finish()
                }
            }
        },
            modifier = Modifier
                .border(width = 1.dp, color = Color.Black)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Text(
                text = "Daftar",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = "Sudah Punya Akun? Login Disini" ,
            fontSize = 14.sp,
            color = Color.Black, modifier = Modifier
                .clickable {
                    mcontext.startActivity(Intent(mcontext, MainActivity::class.java))
                    activity?.finish()
        })

        Spacer(modifier = Modifier.padding(15.dp))

    }

    BackPressHandler()

}

@Composable
private fun BackPressHandler(
    backPressedDispatcher: OnBackPressedDispatcher? =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
) {
    var doubleBackToExit = false
    val mcontext = LocalContext.current
    val activity = (LocalContext.current as? Activity)


    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackToExit){
                    activity?.finish()
                } else {
                    doubleBackToExit = true
                    Toast.makeText(mcontext, "Tekan Lagi Untuk Keluar", Toast.LENGTH_SHORT).show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        kotlin.run {
                            doubleBackToExit = false
                        }
                    }, 2000)
                }
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}




