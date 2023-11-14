@file:OptIn(ExperimentalMaterial3Api::class)

package ua.glebm.smartwaste.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.ui.theme.GuideTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

@Composable
fun GuideSearch(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(
                min = dimensionResource(R.dimen.search_min_height)
            ),
        value = value,
        onValueChange = {
            val query = if (it.length > MAX_SEARCH_QUERY_LENGTH) {
                it.substring(0, MAX_SEARCH_QUERY_LENGTH)
            } else {
                it
            }
            onValueChanged(query)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = GuideTheme.palette.onSurface,
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                style = GuideTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = GuideTheme.palette.onSurface.copy(
                    alpha = SEARCH_HINT_ALPHA,
                ),
            )
        },
        textStyle = GuideTheme.typography.bodyLargeBold,
        colors = TextFieldDefaults.colors(
            cursorColor = GuideTheme.palette.onSurface,
            focusedTextColor = GuideTheme.palette.onSurface,
            unfocusedTextColor = GuideTheme.palette.onSurface,
            focusedContainerColor = GuideTheme.palette.surface,
            unfocusedContainerColor = GuideTheme.palette.surface,
            focusedIndicatorColor = GuideTheme.palette.primary,
        ),
        shape = GuideTheme.shape.huge,
    )
}

@Preview
@Composable
private fun GuideSearchPreview() {
    GuideSearch(
        modifier = Modifier,
        value = "",
        onValueChanged = {},
    )
}

private const val MAX_SEARCH_QUERY_LENGTH = 100
private const val SEARCH_HINT_ALPHA = 0.6f