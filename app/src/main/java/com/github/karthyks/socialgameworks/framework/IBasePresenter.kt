package com.github.karthyks.socialgameworks.framework

/**
 * Created by karthy07 on 12/1/2017.
 */
interface IBasePresenter<in V: BaseView> {
    fun attachView(view: V)

    fun detachView()
}