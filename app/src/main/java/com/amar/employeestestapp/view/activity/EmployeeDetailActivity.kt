package com.amar.employeestestapp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import com.amar.employeestestapp.R
import com.amar.employeestestapp.model.Employee
import com.amar.employeestestapp.util.Util
import com.amar.employeestestapp.view.fragment.EmployeeDetailFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.activity_employee_detail.*
import kotlinx.android.synthetic.main.employee_recyclerview_item.*

class EmployeeDetailActivity : AppCompatActivity() {

    private lateinit var employee: Employee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_detail)
        setSupportActionBar(findViewById(R.id.detail_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        employee = intent.extras?.get(EmployeeDetailFragment.ARG_ITEM_ID) as Employee

        bindDataTView()
    }

    fun bindDataTView() {
        title = employee.name
        positionTV.text = employee.title
        descTV.text = employee.description
        introTV.text = employee.intro

        Glide.with(this).load(employee.getImageUrl()).transition(DrawableTransitionOptions.withCrossFade()).into(profileImage)

        Util.setScaleAnimation(card1)
        Util.setScaleAnimation(card2)


    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}