package com.example.cursofirebaselite.presentation.premium.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Groups3
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.NoAdultContent
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material.icons.filled.VoiceOverOff
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cursofirebaselite.R

@Composable
fun IndexPremium() {

    Box(modifier = Modifier.padding(20.dp)
        .background(MaterialTheme.colorScheme.inverseOnSurface),
        contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                stringResource(R.string.why_premium),
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))
            RowIndexPremium(Icons.Default.VoiceOverOff, stringResource(R.string.premium_feature_1))
            Spacer(modifier = Modifier.height(10.dp))
            RowIndexPremium(Icons.Default.ArrowCircleDown, stringResource(R.string.premium_feature_2))
            Spacer(modifier = Modifier.height(10.dp))
            RowIndexPremium(Icons.Default.PlayCircleFilled, stringResource(R.string.premium_feature_3))
            Spacer(modifier = Modifier.height(10.dp))
            RowIndexPremium(Icons.Default.Headphones, stringResource(R.string.premium_feature_4))
            Spacer(modifier = Modifier.height(10.dp))
            RowIndexPremium(Icons.Default.Groups3, stringResource(R.string.premium_feature_5))
            Spacer(modifier = Modifier.height(10.dp))
            RowIndexPremium(Icons.Default.PlaylistAdd, stringResource(R.string.premium_feature_6))
        }
    }

}
@Composable
fun RowIndexPremium(imageVector: ImageVector, title: String){
    Row(
        modifier = Modifier.padding(8.dp),
    ) {
        Icon(imageVector = imageVector, contentDescription = "Premium Feature 1")
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            title,
            color = White,
            fontSize = 14.sp,
        )
    }

}