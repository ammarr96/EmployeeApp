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

class EmployeeDetailActivity : AppCompatActivity() {

    private lateinit var employee: Employee

    lateinit var profileImageView: AppCompatImageView
    lateinit var positionTV: TextView
    lateinit var descTV: TextView
    lateinit var introTV: TextView
    lateinit var card1: CardView
    lateinit var card2: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_detail)
        setSupportActionBar(findViewById(R.id.detail_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        profileImageView = findViewById(R.id.profileImage)
        positionTV = findViewById(R.id.positionTV)
        descTV = findViewById(R.id.descTV)
        introTV = findViewById(R.id.introTV)
        card1 = findViewById(R.id.card1)
        card2 = findViewById(R.id.card2)

        employee = intent.extras?.get(EmployeeDetailFragment.ARG_ITEM_ID) as Employee

        bindDataTView()
    }

    fun bindDataTView() {
        title = employee.name
        positionTV.text = employee.title
        descTV.text = employee.description
        introTV.text = employee.intro

        Glide.with(this).load(employee.getImageUrl()).transition(DrawableTransitionOptions.withCrossFade()).into(profileImageView);

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