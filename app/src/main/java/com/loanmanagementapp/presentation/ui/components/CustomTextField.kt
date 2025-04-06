package com.loanmanagementapp.presentation.ui.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.loanmanagementapp.R
import com.loanmanagementapp.presentation.ui.theme.PurpleGrey40
import com.loanmanagementapp.presentation.ui.theme.bodyLarge

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit = {},
    placeholderText: String? = null,
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    isEnabled: Boolean = true,
    textColor: Color = Color.Black,
    placeholderTextColor: Color = Color.Black,
    disabledBorderColor: Color = Color.Black,
    enabledBorderColor: Color = PurpleGrey40,
    enabledIconColor: Color = Color.Black,
    textStyle: TextStyle = bodyLarge,
    shape: Shape = RoundedCornerShape(5.dp),
    placeHolderTextStyle: TextStyle = bodyLarge,
    trailingIconId: Int? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onFocusedChanged: (Boolean) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    actionState: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState()
    val focusRequester = FocusRequester()
    val borderColor =
        if (actionState && value.isEmpty()) Color.Red else if (isFocused.value) enabledBorderColor else disabledBorderColor

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = if (actionState && value.isEmpty()) stringResource(R.string.custom_text_field_empty_error) else placeholderText.orEmpty(),
                color = if (actionState && value.isEmpty()) Color.Red else placeholderTextColor,
                style = placeHolderTextStyle
            )
        },
        modifier = modifier
            .focusable(isEnabled)
            .fillMaxWidth()
            .wrapContentHeight()
            .onFocusChanged { focusState ->
                onFocusedChanged(focusState.isFocused)
            }
            .focusRequester(focusRequester),
        enabled = isEnabled,
        readOnly = readOnly,
        interactionSource = interactionSource,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        shape = shape,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            disabledBorderColor = borderColor,
            unfocusedTextColor = textColor
        ),
        singleLine = singleLine,
        textStyle = textStyle,
        trailingIcon = trailingIconId?.let {
            {
                IconButton(onClick = {
                    onTrailingIconClick()
                    focusRequester.requestFocus()
                }) {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = null,
                        tint = enabledIconColor
                    )
                }
            }
        },
        leadingIcon = leadingIcon
    )
}