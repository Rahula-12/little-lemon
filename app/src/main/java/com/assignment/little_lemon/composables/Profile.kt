package com.assignment.little_lemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.assignment.little_lemon.R
import com.assignment.little_lemon.ui.theme.DarkYellow

@Preview
@Composable
fun Profile(modifier: Modifier=Modifier,firstName:String="Rahul",lastName:String="Arora",email:String="abc@gmail.com",logOut:()->Unit={}) {
    Scaffold(
        topBar = {
            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    contentScale= ContentScale.FillWidth,
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "logo",
                    modifier= modifier
                        .width(200.dp)
                        .height(100.dp).padding(top=50.dp)
                )
            }
        }
    ) {
        paddingValues->
        Column(
            modifier=modifier.padding(paddingValues).padding(top=40.dp, start = 10.dp).fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("Profile Information", fontSize = TextUnit(20f,TextUnitType.Sp), fontWeight = FontWeight.SemiBold)
            LabelsAndInfo("First name",firstName)
            LabelsAndInfo("Last name",lastName)
            LabelsAndInfo("Email",email)
            Button(
                onClick = { logOut() },
                modifier= modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonColors(containerColor = DarkYellow,contentColor=Color.Black, disabledContentColor = Color.Black, disabledContainerColor = DarkYellow)
            ) {
                Text("Log out")
            }
        }
    }
}

@Composable
fun LabelsAndInfo(label:String="FirstName",info:String="Rahul") {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(label)
        Text(fontSize = TextUnit(15f,TextUnitType.Sp),text = info, modifier = Modifier.fillMaxWidth().border(
            0.5.dp,color=Color.Black,
            shape = RoundedCornerShape(5.dp)
        ).padding(10.dp))
    }
}