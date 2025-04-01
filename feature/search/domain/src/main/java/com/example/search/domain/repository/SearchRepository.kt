package com.example.search.domain.repository

import com.example.search.domain.model.Recipe
import com.example.search.domain.model.RecipeDetails

interface SearchRepository {
    suspend fun getReceipes(s:String):Result<List<Recipe>>
    suspend fun getReceipesDetails(id:String):Result<RecipeDetails>
}