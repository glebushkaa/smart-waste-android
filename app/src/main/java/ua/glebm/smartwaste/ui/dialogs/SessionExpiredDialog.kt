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
import ua.glebm.smartwaste.ui.theme.GuideTheme

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
                color = GuideTheme.palette.surface,
                shape = GuideTheme.shape.large,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(
                top = GuideTheme.offset.regular,
            ),
            text = stringResource(R.string.session_expired),
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
            text = stringResource(R.string.session_expired_description),
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
        TextButton(
            modifier = Modifier
                .height(
                    dimensionResource(R.dimen.session_expired_button_height)
                )
                .fillMaxWidth(),
            onClick = okClicked,
        ) {
            Text(
                text = stringResource(R.string.ok),
                style = GuideTheme.typography.bodyLargeBold.copy(
                    fontWeight = FontWeight.Medium,
                ),
                color = GuideTheme.palette.onSurface,
            )
        }
    }
}

private const val DESCRIPTION_TEXT_ALPHA = 0.6f
private const val DIVIDER_ALPHA = 0.4f