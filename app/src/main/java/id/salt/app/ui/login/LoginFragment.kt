package id.salt.app.ui.login

import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.salt.app.Nav
import id.salt.app.R
import id.salt.app.base.BaseFragment
import id.salt.app.databinding.LoginFragmentBinding
import id.salt.app.ui.ContainerViewModel
import id.salt.core.other.Status

@AndroidEntryPoint
class LoginFragment: BaseFragment<LoginFragmentBinding>(R.layout.login_fragment) {

    private val viewModel by viewModels<ContainerViewModel>()

    override fun onViewInit() {
        initListener()
    }

    private fun initListener() {
        binding?.buttonLogin?.setOnClickListener {
            login(
                binding?.etLoginEmail?.editText?.text?.toString(),
                binding?.etLoginPassword?.editText?.text?.toString()
            )
        }
    }

    private fun login(email: String?, password: String?) {
        viewModel.login(email, password).observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    hideError()
                    showLoading()
                }
                Status.ERROR -> {
                    showError()
                    hideLoading()
                }
                else -> {
                    Nav(requireActivity()).launchHome()
                    hideLoading()
                }
            }
        }
    }

    private fun hideError(){
        binding?.textLoginFailed?.visibility = View.GONE
    }

    private fun showError() {
        binding?.textLoginFailed?.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding?.buttonLogin?.visibility = View.VISIBLE
        binding?.progressLogin?.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        binding?.buttonLogin?.visibility = View.INVISIBLE
        binding?.progressLogin?.visibility = View.VISIBLE
    }

    override fun setBinding(view: View): LoginFragmentBinding {
        return LoginFragmentBinding.bind(view)
    }
}