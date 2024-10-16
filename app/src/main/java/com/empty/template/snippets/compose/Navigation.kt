package com.empty.template.snippets.compose

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavigationItem(
            modifier = Modifier
                .background(
                    if(currentRoute == NavItems.TravelScreen.path)
                        MaterialTheme.colorScheme.onSecondaryContainer
                    else
                        MaterialTheme.colorScheme.secondaryContainer
                ),
            selected = currentRoute == NavItems.TravelScreen.path,
            onClick = {
                navController.navigate(NavItems.TravelScreen.path) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            icon = { Icon(
                modifier = Modifier
                    .padding(8.dp),
                painter = painterResource(id = NavItems.TravelScreen.icon),
                contentDescription = null,
                tint = if(currentRoute == NavItems.TravelScreen.path)
                    MaterialTheme.colorScheme.inverseOnSurface
                else
                    MaterialTheme.colorScheme.inverseSurface
            )
            },
            label = { Text(
                text = NavItems.TravelScreen.label,
                color = if(currentRoute == NavItems.TravelScreen.path)
                    MaterialTheme.colorScheme.inverseOnSurface
                else
                    MaterialTheme.colorScheme.inverseSurface
            )
            }
        )
    }
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItems.TravelScreen.path
    ) {
        composable(
            route = NavItems.TravelScreen.path,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(400)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(400)
                )
            }

        ) { backStack ->
            val parent = remember(backStack) {
                navController.getBackStackEntry(navController.graph.id)
            }
            val travelScreenViewModel = hiltViewModel<TravelViewModel>(parent)
            TravelScreen(
                navController,
                travelScreenViewModel
            )
        }

        composable(
            route = NavItems.CiteScreen.path,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(400)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(400)
                )
            }
        ) { backStack ->
            val parent = remember(backStack) {
                navController.getBackStackEntry(navController.graph.id)
            }
            val citeScreenViewModel = hiltViewModel<TravelViewModel>(parent)
            CiteScreen(
                navController,
                citeScreenViewModel
            )
        }
    }
}