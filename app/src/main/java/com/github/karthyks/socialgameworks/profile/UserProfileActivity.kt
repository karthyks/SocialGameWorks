package com.github.karthyks.socialgameworks.profile

import android.os.Bundle
import com.github.karthyks.socialgameworks.R
import com.github.karthyks.socialgameworks.framework.BaseActivity

class UserProfileActivity : BaseActivity<UserProfileContract.View,
        UserProfileContract.Presenter>(), UserProfileContract.View {

    override var mPresenter: UserProfileContract.Presenter = UserProfilePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        mPresenter.loadUser()
    }

    override fun populateUser() {

    }
}
