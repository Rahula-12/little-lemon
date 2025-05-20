package com.assignment.little_lemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignment.little_lemon.R

@Preview
@Composable
fun Home(modifier: Modifier=Modifier) {
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

    }
}