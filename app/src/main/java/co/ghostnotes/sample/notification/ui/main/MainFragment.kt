package co.ghostnotes.sample.notification.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.ghostnotes.sample.notification.R
import co.ghostnotes.sample.notification.databinding.MainFragmentBinding
import co.ghostnotes.sample.notification.NotificationHelper
import co.ghostnotes.sample.notification.NotificationID
import co.ghostnotes.sample.notification.util.AndroidVersionUtils

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var notificationHelper: NotificationHelper

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        notificationHelper = NotificationHelper(context!!, NotificationID, AndroidVersionUtils())
        binding.notificationHelper = notificationHelper
    }

}
