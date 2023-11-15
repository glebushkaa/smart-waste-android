package ua.glebm.smartwaste.app

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ua.glebm.smartwaste.core.android.extensions.checkPermission
import ua.glebm.smartwaste.core.android.extensions.navigatePopUpInclusive
import ua.glebm.smartwaste.core.android.extensions.toast
import ua.glebm.smartwaste.session.api.SessionStatus
import ua.glebm.smartwaste.ui.components.AnimatedTopBar
import ua.glebm.smartwaste.ui.dialogs.SessionExpiredDialog
import ua.glebm.smartwaste.ui.navigation.GuideNavHost
import ua.glebm.smartwaste.ui.navigation.components.AnimateBottomNavBar
import ua.glebm.smartwaste.ui.navigation.route.LoginScreenRoute
import ua.glebm.smartwaste.ui.navigation.route.SplashScreenRoute
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuideApp()
        }
    }

    @Composable
    private fun GuideApp() {
        val systemDarkModeEnabled = isSystemInDarkTheme()
        val controller = rememberNavController()

        val darkModeEnabled by viewModel.darkModeFlow
            .collectAsStateWithLifecycle(initialValue = systemDarkModeEnabled)

        val currentEntry by controller
            .currentBackStackEntryFlow
            .collectAsStateWithLifecycle(initialValue = null)

        val sessionStatus by viewModel.sessionStatusFlow
            .collectAsStateWithLifecycle(initialValue = SessionStatus(true))

        var dialogVisible by remember { mutableStateOf(false) }
        val areBarsVisible by remember {
            derivedStateOf { checkAreBarsVisible(currentEntry?.destination?.route) }
        }

        SWTheme(darkModeEnabled ?: systemDarkModeEnabled) {
            val view = LocalView.current
            val backgroundColor = SWTheme.palette.background
            val surfaceColor = SWTheme.palette.surface
            GuideAppContent(
                controller = controller,
                areBarsVisible = areBarsVisible,
            )

            if (dialogVisible) {
                SessionExpiredDialog {
                    dialogVisible = false
                    controller.navigatePopUpInclusive(
                        route = LoginScreenRoute.route,
                        popUpRoute = currentEntry?.destination?.route ?: "",
                    )
                }
            }

            LaunchedEffect(
                key1 = sessionStatus,
            ) {
                if (
                    sessionStatus.alive ||
                    !checkSessionExpiredDialogRoute(currentEntry?.destination?.route)
                ) {
                    return@LaunchedEffect
                }
                dialogVisible = true
            }

            LaunchedEffect(Unit) {
                viewModel.saveSystemDarkMode(systemDarkModeEnabled)
            }

            LaunchedEffect(
                key1 = areBarsVisible,
                key2 = darkModeEnabled,
            ) {
                val window = this@MainActivity.window
                val color = if (areBarsVisible) surfaceColor else backgroundColor
                window.statusBarColor = color.toArgb()
                window.navigationBarColor = color.toArgb()
                WindowCompat.getInsetsController(window, view).apply {
                    isAppearanceLightStatusBars = !(darkModeEnabled ?: systemDarkModeEnabled)
                    isAppearanceLightNavigationBars = !(darkModeEnabled ?: systemDarkModeEnabled)
                }
            }
        }
    }
}

@Composable
private fun GuideAppContent(
    controller: NavHostController,
    areBarsVisible: Boolean,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(SWTheme.palette.background),
        topBar = {
            AnimatedTopBar(
                modifier = Modifier,
                visible = areBarsVisible,
                closeClicked = { controller.popBackStack() },
            )
        },
        content = {
            GuideNavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .background(SWTheme.palette.background)
                    .padding(it),
                controller = controller,
            )
        },
        bottomBar = {
            AnimateBottomNavBar(
                modifier = Modifier,
                controller = controller,
                visible = areBarsVisible,
            )
        },
    )
}

private fun checkSessionExpiredDialogRoute(
    currentRoute: String?,
): Boolean {
    return currentRoute != null &&
        currentRoute != SplashScreenRoute.route &&
        currentRoute != LoginScreenRoute.route
}

private fun checkAreBarsVisible(
    currentRoute: String?,
): Boolean {
    return currentRoute != null &&
        currentRoute != SplashScreenRoute.route &&
        currentRoute != LoginScreenRoute.route
}
