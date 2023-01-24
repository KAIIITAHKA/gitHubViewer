package com.example.githubviewer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager

class AuthFragment : Fragment() {


    val viewModel: AuthViewModel by viewModels {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AuthViewModel(
                    repository = AppRepository(
                        keyValueStorage = KeyValueStorage(PreferenceManager.getDefaultSharedPreferences(requireContext()))
                    )
                ) as T
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.state.observe(this) {
            when (it) {
                is State.Idle -> {
                    //вывести имя
                }
                is State.InvalidInput -> {
                    //вывести ошибку
                }
                State.Loading -> {
                    //показать процесс спиннер
                }
            }
        }


        // передать в эту функ тест из поля
        //viewModel.onSignButtonPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }
}