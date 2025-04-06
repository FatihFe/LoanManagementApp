package com.loanmanagementapp.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.loanmanagementapp.presentation.ui.theme.Purple40
import com.loanmanagementapp.presentation.ui.theme.Purple80
import com.loanmanagementapp.presentation.ui.theme.PurpleGrey80
import com.loanmanagementapp.presentation.ui.theme.bodyLarge

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    text: String? = null,
    buttonType: ButtonType = ButtonType.Primary,
    textStyle: TextStyle = bodyLarge,
    backgroundColor: Color = when (buttonType) {
        ButtonType.Primary -> Purple40
        ButtonType.Secondary -> Transparent
    },
    disabledBackgroundColor: Color = Transparent,
    contentColor: Color = when (buttonType) {
        ButtonType.Primary -> White
        ButtonType.Secondary -> Purple40

    },
    disabledContentColor: Color = when (buttonType) {
        ButtonType.Primary -> Purple80
        ButtonType.Secondary -> Purple80
    },

    borderColor: Color = Black,
    disabledBorderColor: Color = PurpleGrey80,
    borderWidth: Dp = 1.dp,
    shape: Shape = RoundedCornerShape(8.dp),
    padding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
    width: Dp? = null,
) {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    val minClickInterval = 500L

    Button(
        onClick = {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime >= minClickInterval) {
                lastClickTime = currentTime
                onClick()
            }
        },
        modifier = modifier.let { if (width != null) it.width(width) else it.fillMaxWidth() },
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = disabledBackgroundColor,
            contentColor = contentColor,
            disabledContentColor = disabledContentColor
        ),
        border = BorderStroke(borderWidth, if (enabled) borderColor else disabledBorderColor),
        shape = shape,
        contentPadding = padding,
    ) {
        Text(
            text = text.orEmpty(), style = textStyle
        )
    }
}

enum class ButtonType {
    Primary, Secondary
}