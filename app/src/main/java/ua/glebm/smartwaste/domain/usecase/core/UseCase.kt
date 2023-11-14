package ua.glebm.smartwaste.domain.usecase.core

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

interface UseCase<out Type, in Params : UseCase.Params> where Type : Any {

    interface Params
}
