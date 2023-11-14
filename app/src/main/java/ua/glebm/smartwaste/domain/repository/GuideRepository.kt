package ua.glebm.smartwaste.domain.repository

import ua.glebm.smartwaste.model.Guide
import ua.glebm.smartwaste.model.GuideDetails
import ua.glebm.smartwaste.model.Step

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

interface GuideRepository {

    suspend fun searchGuides(query: String): List<Guide>

    suspend fun getGuideDetails(id: String): GuideDetails

    suspend fun getGuideSteps(id: String): List<Step>
}
