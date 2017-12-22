package com.github.karthyks.socialgameworks.framework

/**
 * Created by karthy07 on 12/1/2017.
 */

open class BasePresenter<V : BaseView> : IBasePresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {

    }

    override fun detachView() {

    }
}