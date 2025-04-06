package com.loanmanagementapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.loanmanagementapp.presentation.ui.theme.Purple40
import com.loanmanagementapp.presentation.ui.theme.PurpleGrey80

@Composable
fun LoanCardView(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = PurpleGrey80
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
        ) {
            Box(modifier = Modifier.fillMaxWidth().background(Purple40).padding(12.dp)) {
                Text(
                    text = title, color = Color.White
                )
            }
            HorizontalDivider(
                thickness = 1.dp, color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}