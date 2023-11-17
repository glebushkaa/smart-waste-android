package ua.glebm.smartwaste.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.model.Quest
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@Composable
fun ProfileScreen(
    state: ProfileState,
) {
    ProfileScreenContent(
        state = state,
    )
}

@Composable
private fun ProfileScreenContent(
    state: ProfileState,
) {
    val primaryColor = SWTheme.palette.primary

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SWTheme.palette.background),
        constraintSet = profileConstraintsSet(),
    ) {
        Icon(
            modifier = Modifier
                .size(110.dp)
                .clip(shape = SWTheme.shape.round)
                .background(
                    color = SWTheme.palette.surface,
                    shape = SWTheme.shape.medium,
                )
                .padding(SWTheme.offset.medium)
                .layoutId(PROFILE_ICON),
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = null,
            tint = SWTheme.palette.onSurface,
        )
        Text(
            modifier = Modifier.layoutId(USERNAME_TEXT),
            text = state.username,
            style = SWTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
            ),
            color = SWTheme.palette.onBackground,
        )
        Text(
            modifier = Modifier.layoutId(EMAIL_TEXT),
            text = state.email,
            style = SWTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Normal,
            ),
            color = SWTheme.palette.onBackground,
            textDecoration = TextDecoration.Underline,
        )

        Text(
            modifier = Modifier.layoutId(LEVEL_UP_TEXT),
            text = "Level up",
            style = SWTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Normal,
            ),
            color = SWTheme.palette.onBackground,
        )
        Text(
            modifier = Modifier.layoutId(LEVEL_COMPLETION_TEXT),
            text = "${state.completedLevelProgress}/${state.requiredLevelProgress}",
            style = SWTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Normal,
            ),
            color = SWTheme.palette.onBackground,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    color = SWTheme.palette.primary,
                    shape = RoundedCornerShape(
                        topStart = 40.dp,
                        topEnd = 40.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp,
                    ),
                )
                .layoutId(USER_INFO_BOXES),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(
                    color = SWTheme.palette.surface,
                    shape = SWTheme.shape.large,
                )
                .drawBehind {
                    drawRoundRect(
                        color = primaryColor,
                        size = size.copy(
                            width = size.width * state.completedLevelProgress / state.requiredLevelProgress,
                        ),
                        cornerRadius = CornerRadius(40.dp.toPx()),
                    )
                }
                .layoutId(LEVEL_PROGRESS_BAR),
        )
        UserInfoBox(
            modifier = Modifier.layoutId(USER_BUCKETS_BOX),
            value = state.doneBuckets,
            label = "Buckets",
        )
        UserInfoBox(
            modifier = Modifier.layoutId(USER_LEVEL_BOX),
            value = state.level,
            label = "Level",
        )
        UserInfoBox(
            modifier = Modifier.layoutId(USER_DAYS_BOX),
            value = state.days,
            label = "Days",
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = SWTheme.palette.surface,
                    shape = RoundedCornerShape(
                        topStart = 40.dp,
                        topEnd = 40.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp,
                    ),
                )
                .layoutId(USER_QUESTS_BOX),
        )
        Text(
            modifier = Modifier.layoutId(QUESTS_TITLE),
            text = "Quests",
            style = SWTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold,
            ),
            color = SWTheme.palette.onSurface,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId(USER_QUESTS_LIST),
            verticalArrangement = Arrangement.spacedBy(
                SWTheme.offset.regular,
            ),
        ) {
            items(
                items = state.quests,
                key = { it.id },
            ) { quest ->
                QuestItem(quest = quest)
            }
        }
    }
}

@Composable
private fun UserInfoBox(
    modifier: Modifier = Modifier,
    value: Int,
    label: String,
) {
    Column(
        modifier = modifier
            .size(76.dp)
            .background(
                color = Color.White.copy(
                    alpha = 0.3f,
                ),
                shape = SWTheme.shape.large,
            ),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = value.toString(),
            style = SWTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = SWTheme.palette.onPrimary,
        )
        Text(
            text = label,
            style = SWTheme.typography.labelNormal.copy(
                fontWeight = FontWeight.Normal,
            ),
            color = SWTheme.palette.onPrimary,
        )
    }
}

@Composable
private fun QuestItem(
    modifier: Modifier = Modifier,
    quest: Quest,
) {
    val background = if (quest.completeValue >= quest.totalValue) {
        SWTheme.palette.primary
    } else {
        SWTheme.palette.secondary
    }
    Box(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(
                color = background,
                shape = SWTheme.shape.large,
            ),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = SWTheme.offset.medium),
            text = quest.title,
            style = SWTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
            ),
            color = SWTheme.palette.onPrimary,
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = SWTheme.offset.medium),
            text = "${quest.completeValue}/${quest.totalValue}",
            style = SWTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
            ),
            color = SWTheme.palette.onPrimary,
        )
    }
}

@Composable
private fun profileConstraintsSet(): ConstraintSet {
    val small = SWTheme.offset.small
    val regular = SWTheme.offset.regular
    val large = SWTheme.offset.large
    val huge = SWTheme.offset.huge
    val gigantic = SWTheme.offset.gigantic
    val ultraGigantic = SWTheme.offset.ultraGigantic

    return ConstraintSet {
        val profileIcon = createRefFor(PROFILE_ICON)
        val emailText = createRefFor(EMAIL_TEXT)
        val usernameText = createRefFor(USERNAME_TEXT)
        val userInfoBoxes = createRefFor(USER_INFO_BOXES)

        val userBucketsBox = createRefFor(USER_BUCKETS_BOX)
        val userLevelBox = createRefFor(USER_LEVEL_BOX)
        val userDaysBox = createRefFor(USER_DAYS_BOX)

        val userQuestsBox = createRefFor(USER_QUESTS_BOX)
        val questsTitle = createRefFor(QUESTS_TITLE)
        val userQuestsList = createRefFor(USER_QUESTS_LIST)

        val levelProgressBar = createRefFor(LEVEL_PROGRESS_BAR)
        val levelUpText = createRefFor(LEVEL_UP_TEXT)
        val levelCompletionText = createRefFor(LEVEL_COMPLETION_TEXT)

        constrain(profileIcon) {
            top.linkTo(anchor = parent.top, margin = huge)
            start.linkTo(anchor = parent.start, margin = large)
        }

        constrain(usernameText) {
            top.linkTo(anchor = profileIcon.top, margin = large)
            start.linkTo(anchor = profileIcon.end, margin = regular)
        }

        constrain(emailText) {
            top.linkTo(anchor = usernameText.bottom, margin = small)
            start.linkTo(anchor = profileIcon.end, margin = regular)
        }

        constrain(levelUpText) {
            top.linkTo(anchor = profileIcon.bottom, margin = regular)
            start.linkTo(anchor = parent.start, margin = large)
        }

        constrain(levelCompletionText) {
            top.linkTo(anchor = profileIcon.bottom, margin = regular)
            end.linkTo(anchor = parent.end, margin = large)
        }

        constrain(levelProgressBar) {
            top.linkTo(anchor = levelUpText.bottom, margin = small)
            start.linkTo(anchor = parent.start, margin = large)
            end.linkTo(anchor = parent.end, margin = large)
            width = Dimension.fillToConstraints
        }

        constrain(userInfoBoxes) {
            top.linkTo(anchor = levelProgressBar.bottom, margin = huge)
            start.linkTo(anchor = parent.start)
            end.linkTo(anchor = parent.end)
        }

        constrain(userBucketsBox) {
            top.linkTo(anchor = userInfoBoxes.top, margin = gigantic)
            start.linkTo(anchor = userInfoBoxes.start, margin = large)
        }
        constrain(userLevelBox) {
            top.linkTo(anchor = userInfoBoxes.top, margin = gigantic)
            start.linkTo(anchor = userBucketsBox.end)
            end.linkTo(anchor = userDaysBox.start)
        }
        constrain(userDaysBox) {
            top.linkTo(anchor = userInfoBoxes.top, margin = gigantic)
            end.linkTo(anchor = parent.end, margin = large)
        }

        constrain(userQuestsBox) {
            top.linkTo(anchor = userInfoBoxes.top, margin = ultraGigantic * 2)
            start.linkTo(anchor = parent.start)
            end.linkTo(anchor = parent.end)
            bottom.linkTo(anchor = parent.bottom)
            height = Dimension.fillToConstraints
        }

        constrain(questsTitle) {
            top.linkTo(anchor = userQuestsBox.top, margin = regular)
            start.linkTo(anchor = userQuestsBox.start, margin = large)
            end.linkTo(anchor = userQuestsBox.end, margin = large)
        }

        constrain(userQuestsList) {
            top.linkTo(anchor = questsTitle.bottom, margin = regular)
            start.linkTo(anchor = userQuestsBox.start, margin = large)
            end.linkTo(anchor = userQuestsBox.end, margin = large)
            bottom.linkTo(anchor = parent.bottom)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
    }
}

private const val PROFILE_ICON = "ic_profile"
private const val EMAIL_TEXT = "email_text"
private const val USERNAME_TEXT = "username_text"
private const val USER_INFO_BOXES = "user_info_boxes"
private const val USER_BUCKETS_BOX = "user_buckets_box"
private const val USER_LEVEL_BOX = "user_level_box"
private const val USER_DAYS_BOX = "user_days_box"
private const val USER_QUESTS_BOX = "user_quests_box"
private const val QUESTS_TITLE = "quests_title"
private const val USER_QUESTS_LIST = "user_quests_list"
private const val LEVEL_PROGRESS_BAR = "level_progress_bar"
private const val LEVEL_UP_TEXT = "level_up_text"
private const val LEVEL_COMPLETION_TEXT = "level_completion_text"
