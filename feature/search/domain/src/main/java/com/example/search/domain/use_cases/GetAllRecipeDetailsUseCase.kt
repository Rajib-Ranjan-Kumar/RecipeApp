package com.example.search.domain.use_cases

import com.example.search.domain.model.Recipe
import com.example.search.domain.model.RecipeDetails
import com.example.search.domain.repository.SearchRepository
import gaur.himanshu.common.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class GetAllRecipeDetailsUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(q:String)= flow<NetworkResult<RecipeDetails>> {
        emit(NetworkResult.Loading())
        val response=searchRepository.getReceipesDetails(id = q)
        if (response.isSuccess){
            emit(NetworkResult.Success(data = response.getOrThrow()))
        }
        else{
            emit(NetworkResult.Error(message = response.exceptionOrNull()?.localizedMessage))
        }
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}



//class GetAllRecipeUseCase @Inject constructor(
//    private val searchRepository: SearchRepository
//){
//    operator fun invoke(q:String)= flow<NetworkResult<List<Recipe>>>{
//        emit(NetworkResult.Loading())
//        val response=searchRepository.getReceipes(q)
//        if(response.isSuccess){
//            emit(NetworkResult.Success(data = response.getOrThrow()))
//        }
//        else {
//            emit(NetworkResult.Error(message = response.exceptionOrNull()?.localizedMessage))
//        }
//    }.catch {
//        emit(NetworkResult.Error(it.message.toString()))
//    }.flowOn(Dispatchers.IO)
//}