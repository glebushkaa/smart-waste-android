package ua.glebm.smartwaste.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.window.Dialog
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/31/2023
 */

@Preview
@Composable
fun SessionExpiredDialogPreview() {
    SessionExpiredDialog(
        onDismissRequest = {},
    )
}

@Composable
fun SessionExpiredDialog(
    onDismissRequest: () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        SessionExpiredDialogContent(
            okClicked = onDismissRequest,
        )
    }
}

@Composable
private fun SessionExpiredDialogContent(
    okClicked: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = SWTheme.palette.surface,
                shape = SWTheme.shape.large,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(
                top = SWTheme.offset.regular,
            ),
            text = stringResource(R.string.session_expired),
            style = SWTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Medium,
            ),
            color = SWTheme.palette.onSurface,
        )
        Text(
            modifier = Modifier.padding(
                top = SWTheme.offset.small,
                start = SWTheme.offset.huge,
                end = SWTheme.offset.huge,
            ),
            text = stringResource(R.string.session_expired_description),
            style = SWTheme.typography.bodyMedium,
            color = SWTheme.palette.onSurface.copy(
                alpha = DESCRIPTION_TEXT_ALPHA,
            ),
            textAlign = TextAlign.Center,
        )
        Divider(
            modifier = Modifier
                .padding(top = SWTheme.offset.regular)
                .fillMaxWidth(),
            color = SWTheme.palette.onSurface.copy(
                alpha = DIVIDER_ALPHA,
            ),
        )
        TextButton(
            modifier = Modifier
                .height(
                    dimensionResource(R.dimen.session_expired_button_height),
                )
                .fillMaxWidth(),
            onClick = okClicked,
        ) {
            Text(
                text = stringResource(R.string.ok),
                style = SWTheme.typography.bodyLargeBold.copy(
                    fontWeight = FontWeight.Medium,
                ),
                color = SWTheme.palette.onSurface,
            )
        }
    }
}

private const val DESCRIPTION_TEXT_ALPHA = 0.6f
private const val DIVIDER_ALPHA = 0.4f
