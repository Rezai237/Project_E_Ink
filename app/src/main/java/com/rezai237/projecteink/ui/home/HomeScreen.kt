package com.rezai237.projecteink.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import com.rezai237.projecteink.domain.model.AppInfo
import com.rezai237.projecteink.ui.utils.IconUtils

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: AppViewModel = hiltViewModel()
) {
    val appPages by viewModel.appPages.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onBackground
            )
        } else {
            val pagerState = rememberPagerState(pageCount = { appPages.size })

            Column(modifier = Modifier.fillMaxSize()) {
                // Page Content
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) { pageIndex ->
                    AppGridPage(
                        apps = appPages[pageIndex],
                        onAppClick = { packageName ->
                            viewModel.launchApp(context, packageName)
                        }
                    )
                }

                // Page Indicator
                PageIndicator(
                    pageCount = appPages.size,
                    currentPage = pagerState.currentPage,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun AppGridPage(
    apps: List<AppInfo>,
    onAppClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(apps) { app ->
            AppItem(app = app, onClick = { onAppClick(app.packageName) })
        }
    }
}

@Composable
fun AppItem(
    app: AppInfo,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(4.dp)
    ) {
        // App Icon with Grayscale Filter
        if (app.icon != null) {
            Image(
                bitmap = app.icon.toBitmap().asImageBitmap(),
                contentDescription = app.label,
                colorFilter = IconUtils.getGrayscaleFilter(),
                modifier = Modifier.size(56.dp)
            )
        } else {
            // Fallback placeholder
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color.Gray, shape = CircleShape)
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // App Label
        Text(
            text = app.label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PageIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(pageCount) { index ->
            val isSelected = currentPage == index
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (isSelected) 8.dp else 6.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.onBackground
                        else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                    )
            )
        }
    }
}
