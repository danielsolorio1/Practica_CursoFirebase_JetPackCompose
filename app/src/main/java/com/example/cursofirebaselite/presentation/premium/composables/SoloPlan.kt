package com.example.cursofirebaselite.presentation.premium.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cursofirebaselite.R
import com.example.cursofirebaselite.ui.theme.Pink80
import com.example.cursofirebaselite.ui.theme.SelectedField
import com.example.cursofirebaselite.ui.theme.colorPlaylist

@Composable
fun SoloPlan() {

    Box(
        modifier = Modifier
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.inverseOnSurface),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(160.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Pink80),
                contentAlignment = Alignment.TopStart
            ) {
                Text(
                    text = "Gratis por 3 meses",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.spo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp),

                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = stringResource(id = R.string.premium),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = stringResource(id = R.string.premium_plan_solo),
                color = Pink80,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Gratis por 3 meses",
                color = White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            Text(
                text = "Después, cuesta $129 por mes",
                color = colorPlaylist,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))
            TextSoloPlan(text = "• 1 cuenta premium")
            TextSoloPlan(text = "• Cancela en cualquier momento")
            TextSoloPlan(text = "• Pago por suscripción o pago único")
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width(250.dp),
                    colors = ButtonDefaults.buttonColors(Pink80)
                ) {
                    Text(
                        text = "Probar gratis por 3 meses",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width(250.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inverseOnSurface),
                    border = BorderStroke(1.dp, SelectedField)
                ) {
                    Text(
                        text = "Pago único",
                        fontWeight = FontWeight.Bold,
                        color = White,
                        fontSize = 15.sp
                    )
                }
            }

        }
    }
}

@Composable
fun TextSoloPlan(text: String) {
    Text(
        text = text,
        color = White,
        fontSize = 15.sp,
        modifier = Modifier.padding(horizontal = 15.dp)
    )
}