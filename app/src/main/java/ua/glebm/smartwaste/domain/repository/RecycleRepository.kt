package ua.glebm.smartwaste.domain.repository

import ua.glebm.smartwaste.model.RecyclePoint

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/14/2023
 */

interface RecycleRepository {

    suspend fun getRecyclePoints(): List<RecyclePoint>

    suspend fun getRecyclePointsByCategories(
        categories: List<String>,
    ): List<RecyclePoint>
}
