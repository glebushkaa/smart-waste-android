package ua.glebm.smartwaste.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.ui.theme.GuideBookTheme
import ua.glebm.smartwaste.ui.theme.GuideTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/30/2023
 */

@Preview
@Composable
fun ConfirmationDialogPreview() {
    GuideBookTheme(darkTheme = false) {
        ConfirmationDialogContent(
            title = "Delete account",
            description = "Are you sure you want to delete your account? This action cannot be undone",
            onConfirm = { },
            onCancel = { },
        )
    }
}

@Composable
fun ConfirmationDialog(
    title: String,
    description: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
) {
    Dialog(onDismissRequest = onCancel) {
        ConfirmationDialogContent(
            title = title,
            description = description,
            onConfirm = onConfirm,
            onCancel = onCancel,
        )
    }
}

@Composable
private fun ConfirmationDialogContent(
    title: String,
    description: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = GuideTheme.palette.surface,
                shape = GuideTheme.shape.large,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(
                top = GuideTheme.offset.regular,
            ),
            text = title,
            style = GuideTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Medium,
            ),
            color = GuideTheme.palette.onSurface,
        )
        Text(
            modifier = Modifier.padding(
                top = GuideTheme.offset.small,
                start = GuideTheme.offset.huge,
                end = GuideTheme.offset.huge,
            ),
            text = description,
            style = GuideTheme.typography.bodyMedium,
            color = GuideTheme.palette.onSurface.copy(
                alpha = DESCRIPTION_TEXT_ALPHA
            ),
            textAlign = TextAlign.Center,
        )
        Divider(
            modifier = Modifier
                .padding(top = GuideTheme.offset.regular)
                .fillMaxWidth(),
            color = GuideTheme.palette.onSurface.copy(
                alpha = DIVIDER_ALPHA
            ),
        )
        Row(
            modifier = Modifier
                .height(
                    dimensionResource(R.dimen.confirmation_button_row_height)
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            TextButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                onClick = onCancel,
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = GuideTheme.typography.bodyLargeBold.copy(
                        fontWeight = FontWeight.Medium,
                    ),
                    color = GuideTheme.palette.onSurface,
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(
                        dimensionResource(R.dimen.vertical_confirmation_divider_width)
                    ),
                color = GuideTheme.palette.onSurface.copy(
                    alpha = DIVIDER_ALPHA
                ),
            )
            TextButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                onClick = onConfirm,
            ) {
                Text(
                    text = stringResource(R.string.confirm),
                    style = GuideTheme.typography.bodyLargeBold.copy(
                        fontWeight = FontWeight.Medium,
                    ),
                    color = GuideTheme.palette.error,
                )
            }
        }
    }
}

private const val DESCRIPTION_TEXT_ALPHA = 0.6f
private const val DIVIDER_ALPHA = 0.4f
