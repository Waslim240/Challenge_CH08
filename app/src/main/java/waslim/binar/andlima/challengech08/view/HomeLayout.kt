package waslim.binar.andlima.challengech08.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import waslim.binar.andlima.challengech08.model.film.DataFilmResponseItem
import waslim.binar.andlima.challengech08.view.theme.ChallengeCH08Theme
import waslim.binar.andlima.challengech08.viewmodel.ViewModelFilm

@AndroidEntryPoint
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
                    Home()
                }
            }
        }
    }
}

@Composable
fun Home(){
    val viewModelFilm = viewModel(modelClass = ViewModelFilm::class.java)
    val dataListFilm by viewModelFilm.dataState.collectAsState()
    val mcontext = LocalContext.current

    Text(
        text = "Welcome, Username",
        textAlign = TextAlign.Start,
        color = Color.Black,
        fontStyle = FontStyle.Italic,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(start = 20.dp, top = 20.dp)
            .clickable {
                mcontext.startActivity(Intent(mcontext, MainActivity::class.java))
            }
    )

    Text(
        text = "Logout",
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.End,
        modifier = Modifier.padding(end = 20.dp, top = 20.dp)
    )

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .padding(top = 70.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "HOME",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Column(modifier = Modifier.padding(top = 20.dp)) {
            LazyColumn{
                items(dataListFilm){
                    ListFilm(data = it)
                }
            }
        }
    }
}


@Composable
fun ListFilm(data : DataFilmResponseItem) {
    Column(modifier = Modifier
        .padding(start = 15.dp, end = 15.dp, bottom = 5.dp)) {

        Card(shape = RoundedCornerShape(5.dp),
            backgroundColor = Color.Green,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize() ) {

            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {

                Image(painter = rememberImagePainter(data = data.image),
                    contentDescription = "iconfilm",
                    modifier = Modifier
                        .background(color = Color.Black)
                        .width(125.dp)
                        .height(70.dp)
                        .padding(end = 10.dp))

                Column() {
                    Text(
                        text = "Judul : ${data.title}",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 5.dp))

                    Text(
                        text = "Tanggal Rilis : ${data.createdAt}",
                        color = Color.Red,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 5.dp))

                    Text(
                        text = "Produser : ${data.director}",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 5.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    ChallengeCH08Theme {
        Home()
    }
}