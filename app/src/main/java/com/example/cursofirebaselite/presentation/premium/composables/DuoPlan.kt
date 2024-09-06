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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WorkspacePremium
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
import androidx.compose.ui.graphics.Color.Companion.Yellow
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
import com.example.cursofirebaselite.ui.theme.colorYell
import com.example.cursofirebaselite.ui.theme.negroTransparent

@Composable
fun DuoPlan(){
    Box(
        modifier = Modifier
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.inverseOnSurface),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
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
                text = stringResource(id = R.string.premium_plan_duo),
                color = colorYell,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "$169/mes",
                color = White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))
            TextDuoPlan(text = "• 2 cuentas premium")
            TextDuoPlan(text = "• Cancela en cualquier momento")
            TextDuoPlan(text = "• Pago por suscripción o pago único")
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width(250.dp),
                    colors = ButtonDefaults.buttonColors(colorYell)
                ) {
                    Text(
                        text = "Obtener Premium Duo",
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
fun TextDuoPlan(text: String) {
    Text(
        text = text,
        color = White,
        fontSize = 15.sp,
        modifier = Modifier.padding(horizontal = 15.dp)
    )
}
