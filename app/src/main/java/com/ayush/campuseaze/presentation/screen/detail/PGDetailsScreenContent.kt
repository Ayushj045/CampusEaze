package com.ayush.campuseaze.presentation.screen.detail

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ayush.campuseaze.R
import com.ayush.campuseaze.presentation.components.Loading
import com.ayush.campuseaze.presentation.components.Toast
import com.ayush.campuseaze.presentation.viewmodel.DetailViewModel
import com.ayush.campuseaze.utils.Review
import com.ayush.campuseaze.utils.State

@Composable
fun PGDetailsScreenContent(
    viewModel: DetailViewModel = hiltViewModel(),
    stayId: String
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getStayDetail(stayId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBarSection()
        TopSection()
        DetailsSection(viewModel)
        ActionsSection()
        ReviewsSection()
    }
}

@Composable
fun TopAppBarSection() {
    val activity = LocalContext.current as Activity
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Back Arrow",
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    activity.finish()
                }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Atithi Boys PG",
            fontSize = 18.sp
        )
    }
}


@Composable
fun TopSection() {
    val image: Painter = painterResource(id = R.drawable.detail_image)
    Image(
        painter = image,
        contentDescription = "PG Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .height(200.dp)
            .clip(RoundedCornerShape(bottomStart = 26.dp, bottomEnd = 26.dp))
    )
}

@Composable
fun DetailsSection(viewModel: DetailViewModel) {

    val ctx = LocalContext.current

    viewModel.stayDetail.collectAsState().value.let {
        when(it) {
            is State.Error -> Toast(ctx, it.message)
            State.Loading -> Loading()
            State.None -> {}
            is State.Success -> {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = it.data.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Rating : ${it.data.rating}/5", fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Rent : ₹${it.data.rent}", fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = it.data.address,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun ActionsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { /* TODO: Handle Get Directions */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            modifier = Modifier.weight(1f)
        ) {
            Icon(imageVector = Icons.Default.Directions, contentDescription = "Directions Icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Get Directions")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = { /* TODO: Handle Phone Contact */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
            modifier = Modifier.weight(1f)
        ) {
            Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone Icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Phone Contact")
        }
    }
}

@Composable
fun ReviewsSection() {
    val list = listOf(
        Review(
            name = "Aniket Raj",
            rating = "4/5",
            comment = "Good, but Pricey"
        ), Review(
            name = "Aniket Raj",
            rating = "4/5",
            comment = "Good, but Pricey"
        ), Review(
            name = "Aniket Raj",
            rating = "4/5",
            comment = "Good, but Pricey"
        ), Review(
            name = "Aniket Raj",
            rating = "4/5",
            comment = "Good, but Pricey"
        ), Review(
            name = "Aniket Raj",
            rating = "4/5",
            comment = "Good, but Pricey"
        ),
        Review(
            name = "Shivam",
            rating = "4/5",
            comment = "Nice quality stay. Good Management."
        )
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Reviews", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
        }
        items(list) {
            ReviewItem(name = it.name, rating = it.rating, comment = it.comment)
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            Button(
                onClick = { /* TODO: Handle See All Reviews */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "See All Reviews")
            }
        }
    }
}

@Composable
fun ReviewItem(name: String, rating: String, comment: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.pfp),
                contentDescription = "Reviewer Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = "Rating: $rating", fontSize = 16.sp, color = Color.Gray)
                Text(text = comment, fontSize = 16.sp)
            }
        }
    }
}