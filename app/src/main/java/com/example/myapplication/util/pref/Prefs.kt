package com.example.myapplication.util.pref

import com.example.myapplication.ui.main_screens.main.OptionsEnum

object Prefs {
    private val pref: Pref = HawkImpl

    init {
        pref.init()
    }


    const val LOCAL_SHARED_PREF = "local_shared_pref"
    private const val REMEMBER_ME_STATE = "remember_me_state"
    private const val USER_SESSION = "user_session"
    private const val USER_ID = "user_session"
    private const val USER_OPTION = "user_option"

    fun rememberMeState(): Boolean {
        return pref.get(REMEMBER_ME_STATE, false)
    }

    fun setRememberMeState(rememberState: Boolean) {
        pref.put(REMEMBER_ME_STATE, rememberState)
    }



    fun getUserSession(): String? {
        return pref.get(USER_SESSION, null)
    }

    fun setUserSession(user: String?) {
        pref.put(USER_SESSION, user)
    }

    fun getUserId(): String {
        return pref.get(USER_ID, "")
    }

    fun setUserId(user: String?) {
        pref.put(USER_ID, user)
    }

    fun getUserOption(): OptionsEnum {
        return pref.get(USER_OPTION, OptionsEnum.HAIRDRESSER)
    }

    fun setUserOption(option: OptionsEnum?) {
        pref.put(USER_OPTION, option)
    }
}