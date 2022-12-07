package id.salt.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import id.salt.app.ui.home.HomeFragment
import id.salt.app.ui.login.LoginFragment

class Nav(private var activity: FragmentActivity) {

    private fun replace(fragment: Fragment, name: String = "") {
        activity.supportFragmentManager.commit {
            replace(R.id.main_container, fragment, name)
            if (name.isNotBlank()) addToBackStack(name)
        }
    }

    fun launchHome() {
        replace(HomeFragment(), "HomeFragment")
    }

    fun start() {
        replace(LoginFragment())
    }


}