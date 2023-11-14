package com.moboinfo.trekedu.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.moboinfo.trekedu.R

@Preview
@Composable
fun PreviewLoader() {
    LoadingPage()
}

@Composable
fun LoadingPage() {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (tv, image) = createRefs()
        Text(text = stringResource(id = R.string.setting_up_study_space),
            Modifier
                .fillMaxWidth()
                .constrainAs(tv) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top, 50.dp)
                }
                .clickable {

                }
                .fillMaxWidth(),
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.gil_bold)),
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(id = R.drawable.book_lamp),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(tv.bottom)
                }
                .size(200.dp)
        )
    }

}