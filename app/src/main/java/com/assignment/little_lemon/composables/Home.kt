package com.assignment.little_lemon.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignment.little_lemon.MenuItem2
import com.assignment.little_lemon.R
import com.assignment.little_lemon.ui.theme.DarkGrey
import com.assignment.little_lemon.ui.theme.DarkYellow
import com.assignment.little_lemon.ui.theme.LightGrey

@Preview
@Composable
fun Home(modifier: Modifier=Modifier,menuItems:List<MenuItem2> =listOf()) {
    Scaffold(
        topBar = {
            Row(
                modifier=modifier.fillMaxWidth()
            ) {
                Box(modifier = modifier.fillMaxWidth().weight(2f), contentAlignment = Alignment.Center) {
                    Image(
                        contentScale= ContentScale.FillWidth,
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "logo",
                        modifier= modifier
                            .width(200.dp)
                            .height(100.dp)
                    )
                }
                Image(painter = painterResource(R.drawable.profile),"profile pic",modifier=modifier.size(100.dp))
            }

        }
    ){paddingValues ->
        Column(modifier=modifier.fillMaxSize().padding(paddingValues)) {
            Row(modifier=modifier.fillMaxWidth().wrapContentHeight().background(DarkGrey)) {
                Column(modifier=modifier.weight(2f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Little Lemon", fontSize = TextUnit(40f,TextUnitType.Sp), color = DarkYellow)
                    Text("Chicago", fontSize = TextUnit(30f,TextUnitType.Sp), color = Color.White)
                    Text("We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",color=Color.White, modifier = modifier.padding(top = 10.dp))
                }
                Image(
                    painter = painterResource(R.drawable.hero_image),
                    null,
                    modifier=modifier.weight(1f).padding(top=30.dp,end=10.dp).size(150.dp).clip(RoundedCornerShape(10.dp)),
                    contentScale=ContentScale.FillWidth
                )
            }
            TextField(
                value = "",
                onValueChange = {},
                modifier=modifier.background(DarkGrey).fillMaxWidth().padding(10.dp),
                shape= RoundedCornerShape(10.dp),
                placeholder = {
                    Text("Enter search phase",modifier=modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                },
                leadingIcon={
                    Icon(imageVector = Icons.Default.Search,"search")
                }
            )
            Text(
                "    ORDER FOR DELIVERY!",
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(top=20.dp),
                fontSize=20.sp
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier=modifier.fillMaxWidth().padding(top=10.dp,bottom=10.dp, start = 10.dp,end=10.dp)
            ){
                Button(colors= ButtonColors(containerColor= LightGrey, contentColor = Color.Black, disabledContentColor = LightGrey, disabledContainerColor = Color.Black),onClick = {},shape= RoundedCornerShape(20.dp)) {
                    Text("Starters")
                }

                Button(colors= ButtonColors(containerColor= LightGrey, contentColor = Color.Black, disabledContentColor = LightGrey, disabledContainerColor = Color.Black),onClick = {},shape= RoundedCornerShape(20.dp)) {
                    Text("Mains")
                }
                Button(colors= ButtonColors(containerColor= LightGrey, contentColor = Color.Black, disabledContentColor = LightGrey, disabledContainerColor = Color.Black),onClick = {},shape= RoundedCornerShape(20.dp)) {
                    Text("Desserts")
                }
                Button(colors= ButtonColors(containerColor= LightGrey, contentColor = Color.Black, disabledContentColor = LightGrey, disabledContainerColor = Color.Black),onClick = {},shape= RoundedCornerShape(20.dp)) {
                    Text("Drinks")
                }
            }
        }
    }
}