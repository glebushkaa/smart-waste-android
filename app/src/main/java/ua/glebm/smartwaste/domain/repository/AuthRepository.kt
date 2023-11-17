package ua.glebm.smartwaste.domain.repository

import ua.glebm.smartwaste.model.Quest
import ua.glebm.smartwaste.model.User

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

interface AuthRepository {

    /**
     *@throws AuthException
     * @return access token
     */
    suspend fun login(
        email: String,
        password: String,
    ): String

    /**
     * @throws AuthException
     * @return access token
     */
    suspend fun register(
        username: String,
        email: String,
        password: String,
    ): String

    /**
     * @throws AuthException
     * @return user
     */
    suspend fun getUser(): User

    /**
     * @throws AuthException
     */
    suspend fun deleteAccount()

    suspend fun getQuests(): List<Quest>
}
