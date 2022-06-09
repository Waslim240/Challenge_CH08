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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import waslim.binar.andlima.challengech08.R
import waslim.binar.andlima.challengech08.datastore.DataUserManager
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
                    Text()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Text(){
    val mcontext = LocalContext.current
    val dataUserManager = DataUserManager(mcontext)
    val dataUsername = dataUserManager.username.collectAsState(initial = "username")
    val activity = (LocalContext.current as? Activity)


    Text(
        text = "Welcome, ${dataUsername.value}",
        color = Color.Black,
        fontStyle = FontStyle.Italic,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .width(width = 50.dp)
            .padding(start = 20.dp, end = 2.dp, top = 20.dp)

    )

    Row(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalArrangement = Arrangement.End) {

        IconButton(
            onClick = {
                    mcontext.startActivity(Intent(mcontext, ProfileLayout::class.java))
                    activity?.finish()
            }
        ) {
            Image(painter = painterResource(id = R.drawable.ic_baseline_account_circle_24), contentDescription = "")
        }
    }

    Home()

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Home(){
    val viewModelFilm = viewModel(modelClass = ViewModelFilm::class.java)
    val dataListFilm by viewModelFilm.dataState.collectAsState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

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
private fun ListFilm(data : DataFilmResponseItem) {
    val mcontext = LocalContext.current

    Column(modifier = Modifier
        .padding(start = 15.dp, end = 15.dp, bottom = 5.dp)) {

        Card(shape = RoundedCornerShape(3.dp),
            backgroundColor = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val pindah = Intent(mcontext, DetailLayout::class.java)
                    pindah.putExtra("detailfilm", data)
                    mcontext.startActivity(pindah)
                }
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

                Column {
                    Text(
                        text = data.title,
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 8.dp))

                    Text(
                        text = data.createdAt,
                        color = Color.Red,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 8.dp))

                    Text(
                        text = data.director,
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 8.dp))
                }
            }
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

