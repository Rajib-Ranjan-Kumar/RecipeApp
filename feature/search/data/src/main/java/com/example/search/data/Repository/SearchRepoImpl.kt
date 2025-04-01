package com.example.search.data.Repository

import com.example.search.data.mapper.toDomain
import com.example.search.data.remote.SearchApiService
import com.example.search.domain.model.Recipe
import com.example.search.domain.model.RecipeDetails
import com.example.search.domain.repository.SearchRepository

class SearchRepoImpl(
    private val searchApiService: SearchApiService
) : SearchRepository {
    override suspend fun getReceipes(s: String): Result<List<Recipe>> {
        return try {


        val response = searchApiService.getReceipes(s)

        if (response.isSuccessful) response.body()?.meals?.let {
            Result.success(it.toDomain())
        } ?: run {
            Result.failure(Exception("Error occured"))
        }
        else {
            Result.failure(Exception("error occured"))
        }
        }catch (e:Exception){
            Result.failure(e)
        }

    }

    override suspend fun getReceipesDetails(id: String): Result<RecipeDetails> {
        return try {

            val response = searchApiService.getReceipeDetails(id)

            if (response.isSuccessful) response.body()?.meals?.let {
                if (it.isNotEmpty()) {
                    Result.success(it.first().toDomain())
                } else {
                    Result.failure(Exception("error occured"))
                }
            } ?: run {
                Result.failure(Exception("Error occured"))
            }
            else {
                Result.failure(Exception("error occured"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
