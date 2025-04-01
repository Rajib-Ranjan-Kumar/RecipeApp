package com.example.search.data.mapper

import com.example.search.data.model.ReceipeDTO
import com.example.search.domain.model.Recipe
import com.example.search.domain.model.RecipeDetails

fun List<ReceipeDTO>.toDomain(): List<Recipe> = map {
    Recipe(
        idMeal = it.idMeal.toString(),
        strArea = it.strArea.toString(),
        strMeal = it.strMeal.toString(),
        strMealThumb = it.strMealThumb.toString(),
        strCategory = it.strCategory.toString(),
        strTags = it.strTags ?: "",
        strYoutube = it.strYoutube ?: "",
        strInstructions = it.strInstructions.toString()
    )
}

fun ReceipeDTO.toDomain(): RecipeDetails {
    return RecipeDetails(
        idMeal = idMeal.toString(),
        strArea = strArea.toString(),
        strMeal = strMeal.toString(),
        strMealThumb = strMealThumb.toString(),
        strCategory = strCategory.toString(),
        strTags = strTags ?: "",
        strYoutube = strYoutube ?: "",
        strInstructions = strInstructions.toString(),
        ingredientsPair = getIngredientPairsWithItsMeasure() // ✅ FIXED
    )
}

fun ReceipeDTO.getIngredientPairsWithItsMeasure(): List<Pair<String, String>> {
    val list = mutableListOf<Pair<String, String>>()
    list.add(Pair(strIngredient1.getOrEmpty(), strMeasure1.getOrEmpty()))
    list.add(Pair(strIngredient2.getOrEmpty(), strMeasure2.getOrEmpty()))
    list.add(Pair(strIngredient3.getOrEmpty(), strMeasure3.getOrEmpty()))
    list.add(Pair(strIngredient4.getOrEmpty(), strMeasure4.getOrEmpty()))
    list.add(Pair(strIngredient5.getOrEmpty(), strMeasure5.getOrEmpty()))
    list.add(Pair(strIngredient6.getOrEmpty(), strMeasure6.getOrEmpty()))
    list.add(Pair(strIngredient7.getOrEmpty(), strMeasure7.getOrEmpty()))
    list.add(Pair(strIngredient8.getOrEmpty(), strMeasure8.getOrEmpty()))
    list.add(Pair(strIngredient9.getOrEmpty(), strMeasure9.getOrEmpty()))
    list.add(Pair(strIngredient10.getOrEmpty(), strMeasure10.getOrEmpty()))
    list.add(Pair(strIngredient11.getOrEmpty(), strMeasure11.getOrEmpty()))
    list.add(Pair(strIngredient12.getOrEmpty(), strMeasure12.getOrEmpty()))
    list.add(Pair(strIngredient13.getOrEmpty(), strMeasure13.getOrEmpty()))
    list.add(Pair(strIngredient14.getOrEmpty(), strMeasure14.getOrEmpty()))
    list.add(Pair(strIngredient15.getOrEmpty(), strMeasure15.getOrEmpty()))
    list.add(Pair(strIngredient16.getOrEmpty(), strMeasure15.getOrEmpty()))
    list.add(Pair(strIngredient17.getOrEmpty(), strMeasure15.getOrEmpty()))
    list.add(Pair(strIngredient18.getOrEmpty(), strMeasure15.getOrEmpty()))
    list.add(Pair(strIngredient19.getOrEmpty(), strMeasure15.getOrEmpty()))
    list.add(Pair(strIngredient20.getOrEmpty(), strMeasure15.getOrEmpty()))
    return list
}






fun String?.getOrEmpty() = this?.ifEmpty { "" } ?: ""
