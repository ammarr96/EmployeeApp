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

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [EmployeeListActivity].
 */
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

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don"t need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        /*if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = EmployeeDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EmployeeDetailFragment.ARG_ITEM_ID,
                            intent.getStringExtra(EmployeeDetailFragment.ARG_ITEM_ID))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit()
        }*/
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
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    //navigateUpTo(Intent(this, EmployeeListActivity::class.java))
                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}