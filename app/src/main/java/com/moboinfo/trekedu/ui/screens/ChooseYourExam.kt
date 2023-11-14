package com.moboinfo.trekedu.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.moboinfo.trekedu.ui.theme.blackColor
import com.moboinfo.trekedu.ui.theme.borderColor
import com.moboinfo.trekedu.ui.theme.purpleMain


/**
 * https://github.com/google/accompanist
 */
@Preview
@Composable
fun PreviewChhosee() {
    ChooseYourExamScrn(modifier = Modifier)
}

@Composable
fun ChooseYourExamScrn(modifier: Modifier) {

    ConstraintLayout(
        modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (image, tv1, selectExamChips, etSelectedExam, proceedBtn) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.laptop_with_trics),
            contentDescription = "choose your exam image",
            modifier
                .constrainAs(image) {
                    start.linkTo(parent.start, 20.dp)
                    end.linkTo(parent.end, 20.dp)
                    top.linkTo(parent.top, 40.dp)
                }
                .size(height = 200.dp, width = 250.dp),
        )
        Text(text = stringResource(id = R.string.choose_your_exam),
            modifier
                .constrainAs(tv1) {
                    start.linkTo(parent.start, 20.dp)
                    top.linkTo(image.bottom)
                }
                .clickable {

                }
                .fillMaxWidth(),
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.gil_bold)),
            fontSize = 15.sp)

        ChipList(
            modifier.constrainAs(selectExamChips) {
                start.linkTo(parent.start, 20.dp)
                top.linkTo(image.bottom, 30.dp)
            }, list = listOf(
                "UPSC", "NEET",
                "JEE/MAINS/ADVANCE",
                "CAT", "IELTS",
                "IBPS CLERK","CDS",
                "NDA","RRB"
            ), emptySet()
        )

        Button(onClick = {
        },
            shape = RoundedCornerShape(62),
            modifier = Modifier
                .constrainAs(proceedBtn) {
                    start.linkTo(parent.start, 20.dp)
                    end.linkTo(parent.end, 20.dp)
                    bottom.linkTo(parent.bottom, 30.dp)
                }
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(
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

@Preview
@Composable
fun PreviewChip() {
    ChipList(Modifier, list = listOf("UPSC", "SBI", "IELTS"), emptySet())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipList(modifier: Modifier, list: List<String>, temp: Set<Int>) {

    var multipleChecked by rememberSaveable {
        mutableStateOf(temp)
    }
    com.google.accompanist.flowlayout.FlowRow(
        modifier.fillMaxSize(),
        crossAxisSpacing = 20.dp,
        mainAxisSpacing = 20.dp
    ) {
        list.forEachIndexed { index, s ->
            FilterChip(
                onClick = {
                    multipleChecked =
                        if (multipleChecked.contains(index))
                            multipleChecked.minus(index) else
                            multipleChecked.plus(index)
//                    selected = !selected
                },
                label = {
                    Text(
                        s,
                        fontFamily = FontFamily(Font(R.font.gil_bold)),
                        fontSize = 15.sp,
                        color = blackColor
                    )
                },
                selected = multipleChecked.contains(index),
                leadingIcon = if (multipleChecked.contains(index)) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = if (multipleChecked.contains(
                            index
                        )
                    ) purpleMain else borderColor,
                    borderWidth = if (multipleChecked.contains(index)) 2.dp else 1.dp
                ),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = if (multipleChecked.contains(
                            index
                        )
                    ) purpleMain else Color.Transparent
                ),
                shape = RoundedCornerShape(10.dp)
            )
        }


    }
}