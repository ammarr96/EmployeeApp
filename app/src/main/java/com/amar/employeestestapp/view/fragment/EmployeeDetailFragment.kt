package com.amar.employeestestapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.amar.employeestestapp.R
import com.amar.employeestestapp.model.Employee
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class EmployeeDetailFragment : Fragment() {

    private var employee: Employee? = null

    lateinit var profileImageView: AppCompatImageView
    lateinit var positionTV: TextView
    lateinit var descTV: TextView
    lateinit var introTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                employee = it.get(ARG_ITEM_ID) as Employee?
                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title = employee?.name
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_employee_detail, container, false)

        profileImageView = rootView.findViewById(R.id.profileImage)
        positionTV = rootView.findViewById(R.id.positionTV)
        descTV = rootView.findViewById(R.id.descTV)
        introTV = rootView.findViewById(R.id.introTV)

        employee?.let {
            bindDataTView()
        }

        return rootView
    }

    fun bindDataTView() {
        positionTV.text = employee?.title ?: ""
        descTV.text = employee?.description ?: ""
        introTV.text = employee?.intro ?: ""

        Glide.with(this).load(employee?.getImageUrl()).transition(DrawableTransitionOptions.withCrossFade()).into(profileImageView);

    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }
}