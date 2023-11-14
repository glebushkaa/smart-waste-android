package ua.glebm.smartwaste.core.android.extensions

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.applyIf(
    condition: Boolean,
    modifier: @Composable Modifier.() -> Modifier,
) = composed { if (condition) then(modifier()) else this }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.clickableWithoutRipple(
    onClick: () -> Unit,
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick,
    )
}
