package com.example.myapplication.domain.usecase

import com.example.myapplication.data.local.entity.MenuItemEntity
import com.example.myapplication.domain.model.Resource
import com.example.myapplication.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMenuUseCase  @Inject constructor(
    private val menuRepository: MenuRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<MenuItemEntity>>> = menuRepository.getMenu()
}