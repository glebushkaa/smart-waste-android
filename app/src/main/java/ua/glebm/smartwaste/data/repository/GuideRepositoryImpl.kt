package ua.glebm.smartwaste.data.repository

import ua.glebm.smartwaste.data.mapper.toGuide
import ua.glebm.smartwaste.data.mapper.toGuideDetails
import ua.glebm.smartwaste.data.mapper.toStep
import ua.glebm.smartwaste.data.network.GuidesApi
import ua.glebm.smartwaste.domain.datastore.AuthDataStore
import ua.glebm.smartwaste.domain.exception.AuthException
import ua.glebm.smartwaste.domain.repository.GuideRepository
import ua.glebm.smartwaste.model.Guide
import ua.glebm.smartwaste.model.GuideDetails
import ua.glebm.smartwaste.model.Step
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@Singleton
class GuideRepositoryImpl @Inject constructor(
    private val guidesApi: GuidesApi,
    private val authDataStore: AuthDataStore,
) : GuideRepository {

    private val stepsMap = mutableMapOf<String, List<Step>>()

    override suspend fun getGuideSteps(id: String): List<Step> {
        stepsMap[id]?.let { return it }
        val steps = guidesApi.getGuideSteps(
            token = getAccessToken(),
            id = id,
        ).map { it.toStep() }
        stepsMap[id] = steps
        return steps
    }

    override suspend fun searchGuides(query: String): List<Guide> {
        return guidesApi.searchGuides(
            token = getAccessToken(),
            query = query,
        ).map { it.toGuide() }
    }

    override suspend fun getGuideDetails(id: String): GuideDetails {
        val accessToken = getAccessToken()
        return guidesApi.getGuideDetails(
            token = accessToken,
            id = id,
        ).toGuideDetails()
    }

    private suspend fun getAccessToken(): String {
        return authDataStore.getAccessToken() ?: run {
            val exception = AuthException(
                code = NO_TOKEN_EXCEPTION,
                message = "No token found",
            )
            throw exception
        }
    }

    private companion object {
        const val NO_TOKEN_EXCEPTION = -199
    }
}
