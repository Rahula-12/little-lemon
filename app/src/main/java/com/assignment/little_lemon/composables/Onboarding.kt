package com.assignment.little_lemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignment.little_lemon.R

@Composable
fun Onboarding(modifier: Modifier=Modifier) {
        Scaffold(
            topBar = {
                Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "logo",
                        modifier=modifier.size(100.dp)
                    )
                }
            }
        ) { it->

        }
}

@Preview
@Composable
fun OnboardingPreview() {
    Onboarding()
}