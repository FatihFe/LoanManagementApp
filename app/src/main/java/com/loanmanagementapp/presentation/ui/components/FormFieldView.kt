package com.loanmanagementapp.presentation.ui.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loanmanagementapp.data.domain.model.TextConfig
import com.loanmanagementapp.data.domain.strategy.validation.ValidationConfig
import com.loanmanagementapp.presentation.ui.theme.bodyLarge
import com.loanmanagementapp.presentation.ui.theme.bodyLargeBold
import com.loanmanagementapp.presentation.ui.theme.labelSmall

@Composable
fun FormFieldView(
    config: TextConfig,
    modifier: Modifier = Modifier,
    validationConfig: ValidationConfig?,
) {
    var textState by remember { mutableStateOf("") }
    val isValid by remember { mutableStateOf(validationConfig?.provider?.validate(text = textState)?.isValid) }
    val focusedBorderColor = when {
        textState.isNotEmpty() && validationConfig?.provider?.validate(text = textState)?.isValid == false -> Color.Red
        else -> Color.Black
    }

    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        config.title?.let {
            Text(text = it, color = Color.Black, style = bodyLargeBold)
        }
        OutlinedTextField(
            value = textState,
            onValueChange = {
                textState = it
                validationConfig?.onValueChanged?.invoke(it)
            },
            placeholder = {
                if (textState.isEmpty() && config.placeholder?.isNotEmpty() == true) {
                    Text(
                        text = config.placeholder,
                        style = LocalTextStyle.current.copy(
                            color = Color.Black.copy(alpha = 0.5f),
                            fontSize = 14.sp
                        )
                    )
                }
            },
            modifier = modifier
                .focusable(true)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = Color.Gray,
                disabledBorderColor = Color.Gray
            ),
            singleLine = true,
            textStyle = bodyLarge,
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (textState.isNotEmpty() && validationConfig?.provider?.validate(text = textState)?.isValid == false && !config.errorMessage.isNullOrBlank()) {
            Text(
                text = config.errorMessage,
                color = Color.Red,
                style = labelSmall
            )
        } else if (textState.isNotEmpty() && isValid == false) {
            Text(
                text = validationConfig?.provider?.validate(text = textState)?.errorMessage.orEmpty(),
                color = Color.Gray,
                style = labelSmall
            )
        }
    }
}