package id.salt.app.ui.home

import android.view.View
import id.salt.app.R
import id.salt.app.base.BaseFragment
import id.salt.app.databinding.HomeFragmentBinding

class HomeFragment: BaseFragment<HomeFragmentBinding>(R.layout.home_fragment) {

    override fun onViewInit() {

    }

    override fun setBinding(view: View): HomeFragmentBinding {
        return HomeFragmentBinding.bind(view)
    }

}