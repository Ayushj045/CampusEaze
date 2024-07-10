package com.ayush.campuseaze.presentation.screen.onboarding

import android.content.Context
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NavigateNext
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ayush.campuseaze.R
import com.ayush.campuseaze.presentation.activities.MainActivity
import com.ayush.campuseaze.presentation.components.AnimatedButton
import com.ayush.campuseaze.presentation.viewmodel.OnboardingViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel()
) {

    val pagerState = rememberPagerState(
        pageCount = {
            4
        }
    )
    val scope = rememberCoroutineScope()
    val isFinishVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val ctx = LocalContext.current

    val animatePb = animateFloatAsState(
        targetValue = when (pagerState.currentPage) {
            0 -> 0.25f
            1 -> 0.50f
            2 -> 0.75f
            else -> 0.25f
        },
        label = "progressBar animation",
        finishedListener = {
            isFinishVisible.value = true
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            state = pagerState,
            verticalAlignment = Alignment.Top,
            pageSize = PageSize.Fill
        ) { position ->
            when (position) {
                0 -> PagerScreen { Composable1() }
                1 -> PagerScreen { Composable2() }
                2 -> PagerScreen { Composable3() }
                3 -> PagerScreen { Composable4(
                    pagerState = pagerState,
                    viewModel = viewModel
                ) }
                else -> PagerScreen { Composable1() }
            }
        }

//        if (pagerState.currentPage != 3) {
//            Box(
//                modifier = Modifier
//                    .wrapContentSize()
//                    .align(Alignment.CenterHorizontally)
//                    .padding(bottom = 50.dp),
//                contentAlignment = Alignment.Center
//
//            ) {
//                CircularProgressIndicator(
//                    progress = animatePb.value,
//                    color = MaterialTheme.colorScheme.onSurface,
//                    strokeWidth = 4.dp,
//                    strokeCap = StrokeCap.Round,
//                    modifier = Modifier.size(100.dp)
//                )
//                FilledIconButton(
//                    onClick = {
//                        if (pagerState.currentPage < 3) {
//                            scope.launch {
//                                pagerState.scrollToPage(pagerState.currentPage + 1)
//                            }
//                        }
//                    },
//                    modifier = Modifier
//                        .size(80.dp),
//                    colors = IconButtonDefaults.filledIconButtonColors(
//                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
//                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
//                    )
//
//                ) {
//                    Icon(
//                        imageVector = Icons.Rounded.NavigateNext,
//                        contentDescription = "next button",
//                        modifier = Modifier.size(30.dp)
//                    )
//                }
//            }
//
//        } else {

//        }

    }

}

@Composable
@Preview(showBackground = true)
fun Composable1() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.comp1a),
                contentDescription = "null"
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to",
                    fontFamily = FontFamily(Font(R.font.reg)),
                    fontSize = 20.sp
                )
                Image(
                    painter = painterResource(id = R.drawable.campus),
                    contentDescription = "CampusEase Logo",
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "The ultimate companion for \n" +
                            "students living far from home",
                    fontFamily = FontFamily(Font(R.font.reg)),
                    fontSize = 20.sp
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.comp1c), contentDescription = "null",
                modifier = Modifier.size(300.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.comp1b),
                contentDescription = "null",
            )
        }
    }
}

@Composable
fun Composable2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.comp2b),
                contentDescription = "null",
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Discover and choose from a variety of PGs and Flats that best suit your needs and preferences.",
                fontFamily = FontFamily(Font(R.font.reg)),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(painter = painterResource(id = R.drawable.comp2a), contentDescription = "null")
            Image(
                painter = painterResource(id = R.drawable.comp2c), contentDescription = "null",
            )
        }

    }
}

@Composable
fun Composable3() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.comp3a),
                contentDescription = "null",
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.comp3c),
                contentDescription = ""
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Locate the best mess and laundry services around you for delicious and nutritious meals every day.",
                fontFamily = FontFamily(Font(R.font.reg)),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(painter = painterResource(id = R.drawable.comp3b), contentDescription = "null")

        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.comp3d), contentDescription = "null",
                modifier = Modifier.size(150.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun Composable4(
    pagerState: PagerState,
    viewModel: OnboardingViewModel = hiltViewModel(),
    ctx: Context = LocalContext.current
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.comp4a),
                contentDescription = "null",
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.comp4b),
                contentDescription = ""
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    text = "Find Your Ideal Roommate\u2028Connect with like-minded students and find the perfect roommate to share your space with.",
                    fontFamily = FontFamily(Font(R.font.reg)),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Ready to Simplify Your Student Life?",
                    fontFamily = FontFamily(Font(R.font.bold)),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))
                AnimatedButton(
                    pagerState = pagerState,
                    text = "Finish",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(horizontal = 20.dp)
                ) {
                    viewModel.isOnboarded(completed = true)
                    ctx.startActivity(Intent(ctx, MainActivity::class.java))
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(painter = painterResource(id = R.drawable.comp4c), contentDescription = "null")

        }
    }
}

@Composable
fun PagerScreen(composable: @Composable () -> Unit) {
    composable()
}
