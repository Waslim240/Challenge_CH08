package waslim.binar.andlima.challengech08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import waslim.binar.andlima.challengech08.ui.theme.ChallengeCH08Theme

class HomeLayout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeCH08Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting3("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String) {

    Spacer(modifier = Modifier.padding(top = 50.dp))

    Column(modifier = Modifier
        .padding(start = 15.dp, end = 15.dp, bottom = 5.dp, top = 2.dp)) {

        Card(shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.Green,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically) {

                Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "iconfilm")

                Column() {

                    Text(
                        text = "Judul : The Batman",
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 20.dp))

                    Text(
                        text = "Tanggal Rilis : Maret 01 2022",
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 20.dp))

                    Text(
                        text = "Produser : Dylan Clark, Matt Reeves",
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 20.dp))
                }

            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    ChallengeCH08Theme {
        Greeting3("Android")
    }
}