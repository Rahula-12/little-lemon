package com.assignment.little_lemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.assignment.little_lemon.R
import com.assignment.little_lemon.ui.theme.DarkGrey
import com.assignment.little_lemon.ui.theme.DarkYellow

@Composable
fun Onboarding(modifier: Modifier=Modifier,saveInSharedPref:(String,String,String)->Unit={_,_,_->},navController: NavController= rememberNavController()) {
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
        ) { paddingValues->
            var firstName by remember {
                mutableStateOf("")
            }
            var lastName by remember {
                mutableStateOf("")
            }
            var email by remember {
                mutableStateOf("")
            }
            var registrationStatus by remember {
                mutableStateOf("")
            }
            Column(
                modifier= modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Let's get to know you",modifier= modifier
                    .fillMaxWidth()
                    .background(
                        DarkGrey
                    )
                    .padding(top = 50.dp, bottom = 50.dp), textAlign = TextAlign.Center, color = Color.White, fontSize = TextUnit(30f,
                    TextUnitType.Sp
                )
                )
                Text("Personal Information", fontSize = TextUnit(20f,TextUnitType.Sp), fontWeight = FontWeight.Bold,modifier=modifier.padding(start = 10.dp))
                LabelsAndFields("First Name",firstName){
                    firstName=it
                }
                LabelsAndFields("Last Name",lastName){
                    lastName=it
                }
                LabelsAndFields("Email",email){
                    email=it
                }
                Button(
                    onClick = {
                              if(firstName=="" || lastName=="" || email=="")    registrationStatus="Registration unsuccessful. Please enter all data."
                              else {
                                  saveInSharedPref(firstName,lastName,email)
                                  navController.navigate(Destination.Home.route)
                                  registrationStatus="Registration successful!"
                              }
                    },
                    modifier= modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonColors(containerColor = DarkYellow,contentColor=Color.Black, disabledContentColor = Color.Black, disabledContainerColor = DarkYellow)
                ) {
                    Text("Register")
                }
                Text(text = registrationStatus)
            }
        }
}

@Composable
fun LabelsAndFields(label:String,fieldValue:String,onUpdateField:(String)->Unit={}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(label)
        OutlinedTextField(value = fieldValue, onValueChange = onUpdateField,shape= RoundedCornerShape(10.dp), modifier = Modifier.fillMaxWidth())
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    Onboarding()
}