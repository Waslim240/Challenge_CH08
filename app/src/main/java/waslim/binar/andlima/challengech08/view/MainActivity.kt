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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import waslim.binar.andlima.challengech08.R
import waslim.binar.andlima.challengech08.datastore.DataUserManager
import waslim.binar.andlima.challengech08.model.user.DataUserResponseItem
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
                    Login()
                }
            }
        }
    }
}

@Composable
private fun Login(){
    val viewModelLogin = viewModel(modelClass = ViewModelLogin::class.java)
    val dataListLogin by viewModelLogin.dataState.collectAsState()
    Greeting(dataUser = dataListLogin)
}


@Composable
private fun Greeting(dataUser : List<DataUserResponseItem>) {
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    val scope = rememberCoroutineScope()
    val dataUserManager = DataUserManager(context)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp, top = 30.dp)
            .fillMaxWidth()
    ){

        Text(
            text = "Login",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 23.sp,
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

        Spacer(modifier = Modifier.padding(120.dp))

        Button(onClick = {
            //logika 4
            when {
                email.isEmpty() -> {
                    Toast.makeText(context, "Isi Email Anda", Toast.LENGTH_SHORT).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(context, "Isi Password Anda", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    for (i in dataUser.indices){
                        when {
                            email != dataUser[i].email -> {
                                Toast.makeText(context, "Email Salah", Toast.LENGTH_SHORT).show()
                            }
                            password != dataUser[i].password -> {
                                Toast.makeText(context, "Password Salah", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                scope.launch {
                                    dataUserManager.saveData(
                                        dataUser[i].id,
                                        dataUser[i].username,
                                        email,
                                        password,
                                        dataUser[i].fullName,
                                        dataUser[i].dateOfBirth,
                                        dataUser[i].address,
                                        dataUser[i].image
                                    )
                                }
                                context.startActivity(Intent(context, HomeLayout::class.java))
                                activity?.finish()
                                Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        },
            modifier = Modifier
                .border(width = 1.dp, color = Color.Black)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Text(
                text = "Login",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = "Belum Punya Akun? Daftar Disini" ,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = Color.Black, modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    context.startActivity(Intent(context, RegisterLayout::class.java))
                    activity?.finish()
                }
        )
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

