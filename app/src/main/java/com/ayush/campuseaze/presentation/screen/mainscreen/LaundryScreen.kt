package com.ayush.campuseaze.presentation.screen.mainscreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ayush.campuseaze.R
import com.ayush.campuseaze.data.model.Laundry
import com.ayush.campuseaze.data.model.Stays
import com.ayush.campuseaze.presentation.components.Loading
import com.ayush.campuseaze.presentation.components.Toast
import com.ayush.campuseaze.presentation.viewmodel.LaundryViewModel
import com.ayush.campuseaze.utils.State

@Composable
fun LaundryScreen(
    viewModel: LaundryViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    val laundryList = remember {
        mutableStateListOf(Laundry())
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.getAllLaundries()
    }

    viewModel.allLaundries.collectAsState().value.let {
        when(it) {
            is State.Error -> Toast(ctx, it.message)
            State.Loading -> Loading()
            State.None -> {

            }
            is State.Success -> {
                laundryList.clear()
                laundryList.addAll(it.data)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {
        TitleBarLocationLaundry()
        FilterLaundry(laundryList = laundryList)
    }
}

@Composable
fun FilterLaundry(
    laundryList: List<Laundry>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
//            DDMenuLaundry()
            Spacer(modifier = Modifier.weight(1f))
            FilterButtonLaundry()
        }
        ItemListLaundry(laundryList = laundryList)
    }
}

@Composable
fun FilterButtonLaundry() {
    Row(modifier = Modifier
        .padding(40.dp)
        .clickable {
            //clicking filter button
        }) {
        Icon(
            imageVector = Icons.Default.FilterList, contentDescription = null,
            modifier = Modifier.padding(end = 10.dp)
        )
        Text(text = "Filter", fontWeight = FontWeight.SemiBold)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DDMenuLaundry() {
    val context = LocalContext.current
    val options = arrayOf("PG", "Flat")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(options[0]) }

    Box(
        modifier = Modifier

            .padding(top = 25.dp, start = 30.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }, modifier = Modifier
                .width(95.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(18.dp))
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor(),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent, // Remove underline when focused
                    unfocusedIndicatorColor = Color.Transparent // Remove underline when not focused
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
//                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
//                            action on change of value
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun TitleBarLocationLaundry(modifier: Modifier = Modifier) {

    Row(modifier = Modifier.padding(top = 10.dp, start = 15.dp, end = 15.dp)) {
        Image(
            painter = painterResource(id = R.drawable.direction), contentDescription = null,
            modifier = Modifier
                .padding(top = 10.dp, end = 5.dp, bottom = 10.dp, start = 5.dp)
                .size(30.dp)
        )

        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Your Location", fontWeight = FontWeight.SemiBold)

            Text(text = "Kumaraswamy Layout, Bengaluru, 560078", fontWeight = FontWeight.Normal)

        }

        Spacer(modifier = Modifier.weight(1f))

        Image(painter = painterResource(id = R.drawable.user), contentDescription = null,
            modifier = Modifier
                .padding(top = 10.dp, start = 2.dp)
                .size(35.dp)
                .clickable {
                    //when profile button is clicked
                })


    }

}

@Composable
fun ItemListLaundry(laundryList: List<Laundry>) {
    LazyColumn {
        items(laundryList) { laundry ->
            ItemCardLaundry(laundry = laundry)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCardLaundry(laundry: Laundry) {

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        modifier = Modifier

            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth()

            .background(color = MaterialTheme.colorScheme.background)

            .clickable {
                //clicking action on card
            }
    ) {

        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.laundry),
                contentDescription = null,
                Modifier.fillMaxHeight()
            )

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .width(150.dp)
            ) {
                Text(
                    text = laundry.name, modifier = Modifier.padding(bottom = 5.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Rating : " + laundry.rating + "/5",
                    modifier = Modifier.padding(bottom = 5.dp),
                    fontSize = 14.sp
                )
                Text(
                    text = "Price: ₹${laundry.price}",
                    modifier = Modifier.padding(bottom = 5.dp),
                    fontSize = 14.sp
                )
                Text(
                    text = laundry.address, modifier = Modifier.padding(vertical = 5.dp),
                    fontSize = 14.sp
                )


            }

            Column(modifier = Modifier.padding(top = 10.dp, start = 25.dp)) {
                Text(text = "1 km", fontSize = 14.sp, fontWeight = FontWeight.Bold)

                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)

            }
        }

    }

}