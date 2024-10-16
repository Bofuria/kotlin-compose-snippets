package com.empty.template.snippets.compose.animations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity

fun calculateTargetIndex(
    firstVisibleItemIndex: Int,
    firstVisibleItemScrollOffset: Int,
    itemWidthPx: Float,
    itemCount: Int // Pass the total number of items in the list
): Int {
    // Calculate the total scroll offset in pixels
    val totalScrollOffset = firstVisibleItemIndex * itemWidthPx + firstVisibleItemScrollOffset
    // Calculate the index based on the scroll offset
    var targetIndex = (totalScrollOffset / itemWidthPx).toInt()

    // Determine the fraction of the item that is visible
    val visibleItemFraction = totalScrollOffset % itemWidthPx
    // If more than half of the item is shown, snap to the next item
    if (visibleItemFraction > itemWidthPx / 2) {
        targetIndex++
    }

    // Special case: when the user has scrolled to the end, snap to the last item
    if (targetIndex >= itemCount - 1) {
        targetIndex = itemCount - 1
    }

    return targetIndex
}
@Composable
fun HeroesScreenContent(
    onHeroClick: (Hero) -> Unit,
    heroesList: List<Hero>
) {

    val configuration = LocalConfiguration.current

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val itemWidthPx = with(LocalDensity.current) { configuration.screenWidthDp.dp.toPx() }

    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(heroesList) {
            HeroItem(
                it,
                onHeroClick
            )
        }
    }

    if(heroesList.isNotEmpty()) {
        LaunchedEffect(Unit) {
            // Observes if scrolling is in progress and calculates the target index for snapping
            snapshotFlow { listState.isScrollInProgress }
                .collect { isScrolling ->
                    if (!isScrolling) {
                        delay(100)
                        // Calculate the last item index
                        val lastItemIndex = heroesList.size
                        // Get the layout info of the LazyList
                        val layoutInfo = listState.layoutInfo
                        // Determine if the last item is visible
                        val isLastItemVisible =
                            layoutInfo.visibleItemsInfo.any { it.index == lastItemIndex }
                        if (!isLastItemVisible) {
                            // Calculate the target index for snapping when scrolling stops
                            val targetIndex = calculateTargetIndex(
                                listState.firstVisibleItemIndex,
                                listState.firstVisibleItemScrollOffset,
                                itemWidthPx,
                                heroesList.size
                            )
                            // Animate scrolling to the target index to achieve snapping effect
                            coroutineScope.launch {
                                listState.animateScrollToItem(index = targetIndex)
                            }
                        }
                    }
                }
        }
    }
}