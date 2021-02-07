package com.amar.employeestestapp.view.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import androidx.core.widget.NestedScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amar.employeestestapp.R
import com.amar.employeestestapp.model.Employee
import com.amar.employeestestapp.view.adapter.EmployeeRecyclerViewAdapter
import com.amar.employeestestapp.viewmodel.EmployeeListViewModel

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [EmployeeDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class EmployeeListActivity : AppCompatActivity() {

    private var twoPane: Boolean = false
    private val employeeList : ArrayList<Employee> = arrayListOf()

    lateinit var employeeListViewModel: EmployeeListViewModel
    lateinit var adapter: EmployeeRecyclerViewAdapter
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            twoPane = true
        }

        progressBar = findViewById(R.id.progressBar);

        employeeListViewModel = ViewModelProvider(this).get(EmployeeListViewModel::class.java)

        setupRecyclerView(findViewById(R.id.item_list))

        getData()
    }

    private fun getData() {
        progressBar.visibility = VISIBLE

        if(isInternetAvailable(this))
        {
            employeeListViewModel.getEmployeesFromAPIAndStore()

        }
        else
        {
            //Toast.makeText(this,"No internet found. Showing cached list in the view",Toast.LENGTH_LONG).show()
        }

        employeeListViewModel.getEmployeeData().observe(this, Observer {
            employeeList.addAll(it)
            adapter.setItems(employeeList)
            adapter.notifyDataSetChanged()
            progressBar.visibility = GONE
        })
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        adapter =  EmployeeRecyclerViewAdapter(this, employeeList, twoPane)
        recyclerView.adapter = adapter
    }

    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }

}