package waslim.binar.andlima.challengech08.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.hdodenhof.circleimageview.CircleImageView
import waslim.binar.andlima.challengech08.R
import waslim.binar.andlima.challengech08.view.theme.ChallengeCH08Theme

class DetailLayout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeCH08Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting4()
                }
            }
        }
    }
}

@Composable
fun Greeting4() {

    Column(modifier = Modifier
        .fillMaxWidth().fillMaxSize()
        .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Detail",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.fillMaxWidth())

        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "icondetail",
            modifier = Modifier
                .width(200.dp)
                .height(150.dp)
                .padding(top = 20.dp))

        Text(text = "Judul",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.fillMaxWidth()
                .padding(top = 10.dp))
        Text(text = "Tanggal Rilis",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.fillMaxWidth()
                .padding(top = 10.dp))
        Text(text = "Produser",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.fillMaxWidth()
                .padding(top = 10.dp))

        Text(text = "Deskripsi",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.fillMaxWidth()
                .padding(top = 10.dp))
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview4() {
    ChallengeCH08Theme {
        Greeting4()
    }
}