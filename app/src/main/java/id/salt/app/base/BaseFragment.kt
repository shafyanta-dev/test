package id.salt.app.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<T>(@LayoutRes layoutRes: Int): Fragment(layoutRes) {

    var binding: T? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = setBinding(view)
        onViewInit()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    abstract fun onViewInit()

    abstract fun setBinding(view: View): T

}