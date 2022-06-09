package waslim.binar.andlima.challengech08.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import waslim.binar.andlima.challengech08.R
import waslim.binar.andlima.challengech08.datastore.DataUserManager
import waslim.binar.andlima.challengech08.view.theme.ChallengeCH08Theme

@SuppressLint("CustomSplashScreen")
class SplashScreenLayout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeCH08Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CheckLogin()
                }
            }
        }
    }
}


@Composable
private fun CheckLogin(){
    val activity = (LocalContext.current as? Activity)
    val context = LocalContext.current
    val dataUserManager = DataUserManager(context)
    val data = dataUserManager.username.collectAsState(initial = "username")

    Handler(Looper.getMainLooper()).postDelayed({
        if (data.value != ""){
            context.startActivity(Intent(context, HomeLayout::class.java))
            activity?.finish()
        } else {
            context.startActivity(Intent(context, MainActivity::class.java))
            activity?.finish()
        }
    }, 3000)

    Icon()
}

@Composable
private fun Icon(){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(300.dp),
            painter = painterResource(id = R.drawable.img),
            contentDescription = "iconSplash"
        )

    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview6() {
    ChallengeCH08Theme {
        Icon()
    }
}