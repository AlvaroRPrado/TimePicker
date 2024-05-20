package com.prado.hora

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marosseleng.compose.material3.datetimepickers.date.ui.dialog.DatePickerDialog
import com.marosseleng.compose.material3.datetimepickers.time.domain.noSeconds
import com.marosseleng.compose.material3.datetimepickers.time.ui.dialog.TimePickerDialog
import com.prado.hora.ui.theme.HoraTheme
import java.time.LocalDate
import java.time.LocalTime

class MainActivity : ComponentActivity() {

  //  @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HoraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(32.dp),
                    ) {
                        Column(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            var isTimeDialogShown: Boolean by rememberSaveable {
                                mutableStateOf(false)
                            }
                            val (selectedTime, setSelectedTime) = rememberSaveable {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    mutableStateOf(LocalTime.now().noSeconds())
                                } else {
                                    TODO("VERSION.SDK_INT < O")
                                }
                            }
                            if (isTimeDialogShown) {
                                TimePickerDialog(
                                    onDismissRequest = { isTimeDialogShown = false },
                                    initialTime = selectedTime,
                                    onTimeChange = {
                                        setSelectedTime(it)
                                        isTimeDialogShown = false
                                    },
                                    title = { Text(text = "Select time") },
                                    buttonColors = ButtonDefaults.textButtonColors(contentColor = Color.White),
                                )
                            }
                            Text(
                                text = "Selected time: $selectedTime",
                                style = MaterialTheme.typography.displaySmall,
                            )
                            FilledTonalButton(onClick = { isTimeDialogShown = true }) {
                                Text("Click me to change time")
                            }
                        }

                        Column(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            var isDateDialogShown: Boolean by rememberSaveable {
                                mutableStateOf(false)
                            }
                            val (selectedDate, setSelectedDate) = rememberSaveable {
                                mutableStateOf(LocalDate.now())
                            }
                            if (isDateDialogShown) {
                                DatePickerDialog(
                                    onDismissRequest = { isDateDialogShown = false },
                                    onDateChange = {
                                        setSelectedDate(it)
                                        isDateDialogShown = false
                                    },
                                    title = { Text(text = "Select date") },
                                    buttonColors = ButtonDefaults.textButtonColors(contentColor = Color.Green),
                                )
                            }
                            Text(
                                text = "Selected Date: $selectedDate",
                                style = MaterialTheme.typography.displaySmall,
                            )
                            FilledTonalButton(onClick = { isDateDialogShown = true }) {
                                Text("Click me to change date")
                            }
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HoraTheme {

    }
}