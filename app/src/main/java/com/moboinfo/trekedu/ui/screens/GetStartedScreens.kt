package com.moboinfo.trekedu.ui.screens

import android.annotation.SuppressLint
import android.provider.CalendarContract.Colors
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.contentColorFor
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.delay
import com.moboinfo.trekedu.R
import com.moboinfo.trekedu.ui.theme.purpleMain


data class PagerContent(
    @SerializedName("pagerImage") val pagerImage: Int,
    @SerializedName("title") val title: Int,
    @SerializedName("body") val body: Int,
    @SerializedName("image1") val image1: Int,
    @SerializedName("image2") val image2: Int,
)


val listGetStartedItems = listOf(
    PagerContent(
        R.drawable.books,
        R.string.all_about_exams,
        R.string.know_the_wh,
        0,
        R.drawable.books
    ),
    PagerContent(
        R.drawable.open_books_pencil,
        R.string.fasten_your_learning,
        R.string.get_rid_off,
        0,
        R.drawable.open_books_pencil
    ),
    PagerContent(
        R.drawable.book_lamp,
        R.string.master_your_mind,
        R.string.become_a_master,
        0,
        R.drawable.book_lamp
    )
)

@Preview
@Composable
fun PreviewGetStarted() {
    HorizontalPagerWithIndicators(listGetStartedItems, Modifier)
}


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerWithIndicators(
    items: List<PagerContent>,
    modifier: Modifier,
) {
    val pagerState = rememberPagerState()
    val slider = remember {
        mutableStateOf(true)
    }
    var isHorizontalAnim by remember {
        mutableStateOf(false)
    }
    ConstraintLayout(
        modifier.background(Color.White)
//                .paint(
//                    painterResource(id = R.drawable.background),
//                    contentScale = ContentScale.FillBounds
//                )

    ) {
        val (indicator, image, pager, indicatorParent, getStarted, anim) = createRefs()

        HorizontalPager(
            pageCount = items.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(pager) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (image2) = createRefs()

                Image(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(250.dp)
                        .fillMaxWidth()
                        .constrainAs(image2) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top, 50.dp)
                            end.linkTo(parent.end)
                        },
                    painter = painterResource(id = items[pagerState.currentPage].pagerImage),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = ""
                )
            }
        }

        ConstraintLayout(
            modifier = Modifier
                .background(
                    Color.White,
                    RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .constrainAs(indicatorParent) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        ) {

            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = items.size,

                modifier = Modifier
                    .constrainAs(indicator) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(image.bottom, 20.dp)
                    }
                    .padding(10.dp)
            )
            LaunchedEffect(Unit) {
                while (slider.value) {
                    isHorizontalAnim = true
//                        yield()
                    delay(2000)
//                            isHorizontalAnim = false
                    pagerState.animateScrollToPage(
                        page = (pagerState.currentPage + 1) % (items.size),
                        animationSpec = tween(600)
                    )
                }
            }
            AnimatedVisibility(visible = isHorizontalAnim,
                enter = slideInHorizontally(initialOffsetX = { it / 2 }),
                exit = slideOutHorizontally(targetOffsetX = { -it }),

                modifier = Modifier
                    .fillMaxWidth()

                    .constrainAs(anim) {
                        top.linkTo(indicator.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(getStarted.top)
                    }
            ) {
                Column {
                    Text(
                        text = stringResource(id = items[pagerState.currentPage].title),
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black,
//                                style = (MaterialTheme.typography.titleLarge),
                        textAlign = TextAlign.Center,
                        fontSize = 27.sp,
                        fontFamily = FontFamily(Font(R.font.gil_bold))

                    )

                    Text(
                        text = stringResource(id = items[pagerState.currentPage].body),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp, vertical = 8.dp),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
//                                style = (MaterialTheme.typography.titleSmall),
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.gil_midum))


                    )
                }

            }

            Button(onClick = {
            },
                shape = RoundedCornerShape(62),
                modifier = Modifier
                    .constrainAs(getStarted) {
                        start.linkTo(parent.start, 20.dp)
                        end.linkTo(parent.end, 20.dp)
                        bottom.linkTo(parent.bottom, 30.dp)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = (
                        purpleMain
                    )
                )) {
                Text(
                    text = "Proceed",
//                            style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                )
            }
        }

    }
}


