package ua.glebm.smartwaste.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@Preview
@Composable
fun ProfileScreenPreview() {
    SWTheme(darkTheme = true) {
        ProfileScreen(
            state = ProfileState(),
        )
    }
}

@Composable
fun ProfileScreen(
    state: ProfileState,
    sendEvent: (ProfileEvent) -> Unit = {},
) {
    ProfileScreenContent(
        state = state,
        sendEvent = sendEvent,
    )
}

@Composable
private fun ProfileScreenContent(
    state: ProfileState,
    sendEvent: (ProfileEvent) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SWTheme.palette.background),
    ) {
        AsyncImage(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = SWTheme.offset.huge)
                .size(80.dp)
                .clip(shape = SWTheme.shape.round)
                .background(
                    color = SWTheme.palette.surface,
                    shape = SWTheme.shape.medium,
                )
                .padding(SWTheme.offset.medium),
            model = state.picturePath ?: R.drawable.ic_profile,
            contentDescription = null,
        )
    }
}
