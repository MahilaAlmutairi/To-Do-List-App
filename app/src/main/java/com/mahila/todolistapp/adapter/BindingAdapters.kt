package com.mahila.todolistapp.adapter

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mahila.todolistapp.ui.MainFragmentDirections
import com.mahila.todolistapp.R
import com.mahila.todolistapp.data.model.Task

class BindingAdapters {

    companion object {

        @BindingAdapter("android:navigateToInsertTaskInfoFragment")
        @JvmStatic
        fun navigateToInsertTaskInfoFragment(view: FloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if(navigate) {
                    view.findNavController().navigate(R.id.action_mainFragment_to_insertTaskInfoFragment)
                }
            }
        }


        @BindingAdapter("android:sendDataToEditTaskInfoFragment")
        @JvmStatic
        fun sendDataToEditTaskInfoFragment(view: ConstraintLayout, currentTask: Task) {
            view.setOnClickListener {
                val action =
                    MainFragmentDirections.actionMainFragmentToEditFragment(currentTask)
                view.findNavController().navigate(action)
            }
        }

    }

}