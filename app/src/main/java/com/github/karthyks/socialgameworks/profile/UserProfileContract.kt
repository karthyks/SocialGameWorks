package com.github.karthyks.socialgameworks.profile

import com.github.karthyks.socialgameworks.framework.BaseView
import com.github.karthyks.socialgameworks.framework.IBasePresenter

/**
 * Created by karthy07 on 12/1/2017.
 */
object UserProfileContract {

    interface View: BaseView {
        fun populateUser()
    }

    interface Presenter: IBasePresenter<View> {
        fun loadUser()
    }
}