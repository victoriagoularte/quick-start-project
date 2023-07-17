package com.example.recipe.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipe.domain.model.Recipe
import com.example.recipe.ui.theme.RecipeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getRecipeList()
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                HomeScreen(
                    recipeList = state.recipeList,
                    textFilter = state.textFilter,
                    onFilterChange = viewModel::updateFilter
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    textFilter: String,
    recipeList: List<Recipe>,
    onFilterChange: (String) -> Unit
) {
    RecipeTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                TextField(
                    value = textFilter,
                    onValueChange = onFilterChange,
                    label = { Text("Search") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            modifier = Modifier.padding(8.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(recipeList) { item ->
                        RecipeCard(item)
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(96.dp),
        contentAlignment = Alignment.BottomEnd,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe.image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = recipe.name,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val recipeList = listOf(
        Recipe("Fricasse", "https://www.ocado.com/cmscontent/recipe_image_large/33362787.png?awuq"),
        Recipe("Fricasse", "https://www.ocado.com/cmscontent/recipe_image_large/33362787.png?awuq"),
        Recipe("Fricasse", "https://www.ocado.com/cmscontent/recipe_image_large/33362787.png?awuq"),
        Recipe("Fricasse", "https://www.ocado.com/cmscontent/recipe_image_large/33362787.png?awuq"),
    )
    RecipeTheme {
        HomeScreen(
            recipeList = recipeList,
            textFilter = "",
            onFilterChange = {}
        )
    }
}
