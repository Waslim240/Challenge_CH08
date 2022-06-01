package waslim.binar.andlima.challengech08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import waslim.binar.andlima.challengech08.dataclass.DataFilm
import waslim.binar.andlima.challengech08.dataclass.FilmRepository
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
                    val filmRepository = FilmRepository()
                    val allData = filmRepository.getDataFilm()
                    LazyColumn(){
                        items(items = allData){ dataFilm ->
                            Greeting3(dataFilm = dataFilm)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting3(dataFilm: DataFilm) {

    Column(modifier = Modifier
        .padding(start = 15.dp, end = 15.dp, bottom = 5.dp, top = 2.dp)) {

        Card(shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.White,
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
                        text = "Judul : ${dataFilm.judul}",
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 20.dp))

                    Text(
                        text = "Tanggal Rilis : ${dataFilm.tanggalRilis}",
                        color = Color.Magenta,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 20.dp))

                    Text(
                        text = "Produser : ${dataFilm.produser}",
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
        Greeting3(dataFilm = DataFilm("", "", ""))
    }
}