package com.example.cursofirebaselite.presentation.premium

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cursofirebaselite.R
import com.example.cursofirebaselite.presentation.home.Screen
import com.example.cursofirebaselite.presentation.premium.composables.DuoPlan
import com.example.cursofirebaselite.presentation.premium.composables.FamilyPlan
import com.example.cursofirebaselite.presentation.premium.composables.IndexPremium
import com.example.cursofirebaselite.presentation.premium.composables.SoloPlan
import com.example.cursofirebaselite.presentation.premium.composables.StudentPlan
import com.example.cursofirebaselite.ui.theme.Black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Premium() {

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black)
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    IndexPremium()
                    //Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        stringResource(R.string.plans_avaiable),
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    SoloPlan()
                    StudentPlan()
                    DuoPlan()
                    FamilyPlan()
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}