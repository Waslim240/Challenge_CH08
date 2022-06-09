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
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import waslim.binar.andlima.challengech08.R
import waslim.binar.andlima.challengech08.datastore.DataUserManager
import waslim.binar.andlima.challengech08.view.theme.ChallengeCH08Theme

class ProfileLayout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeCH08Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting3()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Greeting3() {
    val mcontext = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    val dataUserManager = DataUserManager(mcontext)
    val scope = rememberCoroutineScope()
    val dataUsername = dataUserManager.username.collectAsState(initial = "username")
    val dataNamaLengkap = dataUserManager.fullName.collectAsState(initial = "fullName")
    val dataUmur = dataUserManager.dateOfbirth.collectAsState(initial = "umur")
    val dataAlamat = dataUserManager.address.collectAsState(initial = "address")
    val dataImage = dataUserManager.image.collectAsState(initial = "image")


    Row(
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = {
                mcontext.startActivity(Intent(mcontext, HomeLayout::class.java))
                activity?.finish()
            }
        ) {
            Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24), contentDescription = "iconback")
        }
    }

    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp, top = 30.dp)
            .fillMaxWidth()
            .fillMaxHeight()){

        Text(text = "Profile",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 23.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.fillMaxWidth())

        Image(
            painter = rememberImagePainter(data = dataImage.value),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 30.dp, bottom = 20.dp)
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )

        OutlinedTextField(
            value = dataUsername.value,
            onValueChange = { dataUsername.value },
            placeholder = { Text(dataUsername.value) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Username") }
        )

        OutlinedTextField(
            value = dataNamaLengkap.value,
            onValueChange = { dataNamaLengkap.value },
            placeholder = { Text(dataNamaLengkap.value) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nama Lengkap") }
        )

        OutlinedTextField(
            value = dataUmur.value,
            onValueChange = { dataUmur.value },
            placeholder = { Text(dataUmur.value) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Tanggal Lahir") }
        )

        OutlinedTextField(
            value = dataAlamat.value,
            onValueChange = { dataAlamat.value },
            placeholder = { Text(dataAlamat.value) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Alamat") }
        )

        Spacer(modifier = Modifier.padding(top = 150.dp))

            Button(
                onClick = {
                    scope.launch {
                        dataUserManager.logout()
                        Toast.makeText(mcontext, "Berhasil Keluar", Toast.LENGTH_SHORT).show()
                        mcontext.startActivity(Intent(mcontext, SplashScreenLayout::class.java))
                        activity?.finish()
                    }
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(width = 1.dp, color = Color.Black)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text(
                    text = "Logout",
                    color = Color.White
                )

            }
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

