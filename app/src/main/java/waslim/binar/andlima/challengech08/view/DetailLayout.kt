package waslim.binar.andlima.challengech08.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import waslim.binar.andlima.challengech08.R
import waslim.binar.andlima.challengech08.model.film.DataFilmResponseItem
import waslim.binar.andlima.challengech08.view.theme.ChallengeCH08Theme

class DetailLayout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val getDataFilm = intent.getParcelableExtra<DataFilmResponseItem>("detailfilm")

        setContent {
            ChallengeCH08Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Detail(getDataFilm!!)
                }
            }
        }
    }
}

@Composable
private fun Detail(detail : DataFilmResponseItem) {
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {

        FloatingActionButton(
            modifier = Modifier
                .size(80.dp)
                .padding(15.dp),
            onClick = {
            }
        ) {
            Image(
                modifier = Modifier.clickable {

                } ,
                painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                contentDescription = "fav"
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp, top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Detail",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.fillMaxWidth())

        Box {
            Image(
                painter = rememberImagePainter(data = detail.image),
                contentDescription = "imageDetail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_baseline_play_circle_filled_24),
                contentDescription = "playVideo",
                modifier = Modifier
                    .padding(top = 105.dp, start = 80.dp)
                    .clickable {
                        context.startActivity(Intent(context, VideoPlayer::class.java))
                    }
            )
        }


        Text(
            text = detail.title,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )

        Text(
            text = detail.createdAt,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )

        Text(
            text = detail.director,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )

        Text(
            text = detail.description,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 15.sp,
            textAlign = TextAlign.Justify,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview4() {
    ChallengeCH08Theme {
        Detail(detail = DataFilmResponseItem("","","","","",""))
    }
}