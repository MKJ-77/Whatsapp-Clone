package com.mkj.whatsapp.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Country(val name: String, val code: String)


@Composable
@Preview(showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
fun UserRegistrationScreen() {
    val countryList = listOf(
        Country("India", "+91"),
        Country("United States", "+1"),
        Country("United Kingdom", "+44"),
        Country("Canada", "+1"),
        Country("Australia", "+61")
    )

    var phoneNumber by remember { mutableStateOf("") }
    var selectedCountry by remember {
        mutableStateOf(Country("India", "+91"))
    }
    var showCountrySheet by remember { mutableStateOf(false) }

    if (showCountrySheet) {
        ModalBottomSheet(
            onDismissRequest = { showCountrySheet = false }
        ) {
            CountryPicker(
                countries = countryList,
                onSelect = {
                    selectedCountry = it
                    showCountrySheet = false
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Enter your phone number",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF075E54)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Country selector
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showCountrySheet = true }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedCountry.name,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.width(18.dp))
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.size(27.dp)
                )
            }

            // underline
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .height(1.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Color(0xFF25D366))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Phone input
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedCountry.code,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            TextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                placeholder = { Text("phone number") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.weight(.75f),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFF25D366),
                    unfocusedIndicatorColor = Color.LightGray
                )
            )
        }


        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { /* verify */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF075E54)
            )
        ) {
            Text("Next", color = Color.White)
        }
    }


}

@Composable
fun CountryPicker(
    countries: List<Country>,
    onSelect: (Country) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Select country",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        countries.forEach { country ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelect(country) }
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = country.name, fontSize = 16.sp)
                Text(text = country.code, color = Color.Gray)
            }
        }
    }

}