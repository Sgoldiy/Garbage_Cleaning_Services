package com.cpimca.gogreen

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cpimca.gogreen.ui.theme.GoGreenTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoGreenTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                ) {
                    val viewModel = CleaningServiceViewModel()
                    val context = LocalContext.current

                    LaunchedEffect(Unit) {
                        viewModel.fetchCleaningServiceRequests()
                    }


                    val authViewModel = viewModel<AuthViewModel>()
                    authViewModel.initSharedPreferences(LocalContext.current)
                    val firebaseAuth = FirebaseAuth.getInstance()

                    val isLoggedIn by remember {
                        mutableStateOf(firebaseAuth.currentUser != null)
                    }
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = if (isLoggedIn) Route.HomeScreen else if (authViewModel.isLoggedIn) Route.LoginScreen else Route.Splash
                    ) {
                        composable(route = Route.Splash) {
                            Splash(navigateToOnBoarding = {
                                navController.navigate(Route.OnBoarding) {
                                    popUpTo(Route.Splash) {
                                        inclusive = true
                                    }
                                }
                            })
                        }
                        composable(route = Route.LoginScreen) {
                            LoginScreen(navController = navController)
                        }

                        composable(route = Route.OnBoarding) {
                            OnBoarding(navController = navController)
                        }
                        composable(route = Route.RegisterScreen) {
                            RegisterScreen(navController = navController)
                        }
                        composable(route = Route.HomeScreen) {
                            HomeScreen(navController = navController, onItemSelected = { })
                        }
                        composable(route = Route.DashBoard) {
                            DashBoard(navController = navController)
                        }
                        composable(route = Route.CleaningServiceAdmin) {
                            UserRequest(navController = navController)
                        }
                        composable(route = Route.ConfirmService) {
                            ConfirmService(navController = navController)
                        }
                        composable(route = Route.DailyPickUpServiceAdmin) {
                            UserRequestPickUp(navController = navController)
                        }
                        composable(route = Route.DailyPickUpServiceConfirmAdmin) {
                            DailyPickUpServiceConfirmAdmin(navController = navController)
                        }
                        composable(route = Route.DustbinServiceConfirmAdmin) {
                            DustbinServiceConfirmAdmin(navController = navController)
                        }
                        composable(route = Route.DustbinServiceAdmin) {
                            UserRequestDustbin(navController = navController)
                        }
                        composable(route = Route.AddWorker) {
                            AddWorker(navController = navController)
                        }
                        composable(route = Route.ManageWorker) {
                            ManageWorker(navController = navController)
                        }
                        composable(route = Route.ComplainScreen) {
                            ComplainScreen(navController = navController)
                        }
                        composable(route = Route.ComplainScreen2) {
                            ComplainScreen2(navController = navController)
                        }
                        composable(route = Route.ComplainScreen3) {
                            ComplainScreen3(navController = navController)
                        }
                        composable(route = Route.FeedbackScreen) {
                            FeedbackScreen(navController = navController)
                        }
                        composable(route = Route.FeedbackAndComplainForAdmin) {
                            FeedbackAndComplainForAdmin(navController = navController)
                        }
                        composable(route = Route.ComplainScreenAdmin) {
                            ComplainScreenAdmin(navController = navController)
                        }
                        composable(route = Route.ComplainScreen2Admin) {
                            ComplainScreen2Admin(navController = navController)
                        }
                        composable(route = Route.ComplainScreen3Admin) {
                            ComplainScreen3Admin(navController = navController)
                        }
                        composable(route = Route.FeedbackScreenAdmin) {
                            FeedbackScreenAdmin(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


object Route {
    const val Splash = "Splash"
    const val LoginScreen = "LoginScreen"
    const val OnBoarding = "OnBoarding"
    const val RegisterScreen = "RegisterScreen"
    const val HomeScreen = "HomeScreen"
    const val DashBoard = "DashBoard"
    const val CleaningService = "CleaningService"
    const val DustbinService = "DustbinService"
    const val DailyPickUpService = "DailyPickUpService"
    const val CleaningServiceUser = "CleaningServiceUser"
    const val CleaningServiceAdmin = "CleaningServiceAdmin"
    const val ConfirmService = "ConfirmService"
    const val ConfirmServiceUser = "ConfirmServiceUser"
    const val DailyPickUpServiceUser = "DailyPickUpServiceUser"
    const val DailyPickUpServiceAdmin = "DailyPickUpServiceAdmin"
    const val DailyPickUpServiceConfirmAdmin = "DailyPickUpServiceConfirmAdmin"
    const val DailyPickUpServiceConfirmUser = "DailyPickUpServiceConfirmUser"
    const val DustbinServiceUser = "DustbinServiceUser"
    const val DustbinServiceConfirmUser = "DustbinServiceConfirmUser"
    const val DustbinServiceAdmin = "DustbinServiceAdmin"
    const val DustbinServiceConfirmAdmin = "DustbinServiceConfirmAdmin"
    const val AddWorker = "AddWorker"
    const val ManageWorker = "ManageWorker"
    const val ComplainScreen = "ComplainScreen"
    const val ComplainScreen2 = "ComplainScreen2"
    const val ComplainScreen3 = "ComplainScreen3"
    const val FeedbackScreen = "FeedbackScreen"
    const val FeedbackAndComplainForAdmin = "FeedbackAndComplainForAdmin"
    const val FeedbackScreenAdmin = "FeedbackScreenAdmin"
    const val ComplainScreenAdmin = "ComplainScreenAdmin"
    const val ComplainScreen2Admin = "ComplainScreen2Admin"
    const val ComplainScreen3Admin = "ComplainScreen3Admin"

}

@Composable
fun Splash(
    navigateToOnBoarding: () -> Unit,
) {
    var navigated by remember {
        mutableStateOf(false)
    }

    val offsetY = remember {
        Animatable(0f)
    }

    LaunchedEffect(true) {
        delay(2400)
        navigated = true
        navigateToOnBoarding()
    }

    LaunchedEffect(navigated) {
        if (!navigated) {
            offsetY.animateTo(-330f, animationSpec = tween(durationMillis = 2200))
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier
                .height(330.dp)
                .width(260.dp)
                .offset(y = offsetY.value.dp),
            painter = painterResource(id = R.drawable.splas),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

    }
}

fun checkIfUserIsLoggedIn(): Boolean {
    // Check if there is a currently authenticated user
    val currentUser = FirebaseAuth.getInstance().currentUser
    return currentUser != null
}

