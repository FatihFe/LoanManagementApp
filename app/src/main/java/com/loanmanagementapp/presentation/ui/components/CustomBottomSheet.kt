package com.loanmanagementapp.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheet(
    onDismiss: () -> Unit,
    onContinue: () -> Unit,
    title: String,
    approveButtonTitle: String,
    unApproveButtonTitle: String
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        sheetState = sheetState,
        dragHandle = null
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, start = 16.dp, bottom = 40.dp, top = 20.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
            )
            Spacer(modifier = Modifier.height(height = 18.dp))
            HorizontalDivider(
                thickness = 1.dp, color = Color.Gray
            )
            Spacer(modifier = Modifier.height(height = 18.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                PrimaryButton(
                    onClick = {
                        coroutineScope.launch {
                            onContinue()
                        }
                    }, text = approveButtonTitle,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(width = 12.dp))
                PrimaryButton(
                    onClick = {
                        onDismiss()
                    }, text = unApproveButtonTitle,
                    buttonType = ButtonType.Secondary,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(height = 16.dp))
        }
    }
}