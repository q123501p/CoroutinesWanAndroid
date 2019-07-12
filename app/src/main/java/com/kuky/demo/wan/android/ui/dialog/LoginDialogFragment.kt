package com.kuky.demo.wan.android.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.kuky.demo.wan.android.R
import com.kuky.demo.wan.android.base.BaseDialogFragment
import com.kuky.demo.wan.android.data.MainRepository
import com.kuky.demo.wan.android.databinding.DialogLoginBinding
import com.kuky.demo.wan.android.ui.main.MainModelFactory
import com.kuky.demo.wan.android.ui.main.MainViewModel
import kotlinx.android.synthetic.main.dialog_login.*
import org.jetbrains.anko.toast

/**
 * @author kuky.
 * @description
 */
class LoginDialogFragment : BaseDialogFragment<DialogLoginBinding>() {

    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders
            .of(requireActivity(), MainModelFactory(MainRepository()))
            .get(MainViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.dialog_login

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding.holder = this@LoginDialogFragment
    }

    fun login(view: View) {
        val username = user_name.text.toString()
        val password = password.text.toString()

        if (username.isBlank() || password.isBlank()) {
            requireContext().toast("请输入完整")
        } else {
            mViewModel.login(username, password)

            // TODO("登录提示待完成")
//            mViewModel.hasLogin.observe(this, Observer<Boolean> {
//                requireContext().toast(if (it) "登录成功" else "登录出错")
//            })
            dialog?.dismiss()
        }
    }

    fun register(view: View) {
        dialog?.dismiss()
        RegisterDialogFragment().show(requireActivity().supportFragmentManager, "register")
    }
}