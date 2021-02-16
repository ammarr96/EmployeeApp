package com.amar.employeestestapp.view.adapter

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amar.employeestestapp.R
import com.amar.employeestestapp.model.Employee
import com.amar.employeestestapp.service.EventService
import com.amar.employeestestapp.service.EventType
import com.amar.employeestestapp.util.Util
import com.amar.employeestestapp.view.activity.EmployeeDetailActivity
import com.amar.employeestestapp.view.activity.EmployeeListActivity
import com.amar.employeestestapp.view.fragment.EmployeeDetailFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.employee_recyclerview_item.view.*


class EmployeeRecyclerViewAdapter(private val parentActivity: EmployeeListActivity,
                                  private var values: List<Employee>,
                                  private val twoPane: Boolean) : RecyclerView.Adapter<EmployeeRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Employee

            //track event for opening user ptofile
            EventService.sendEvent(EventType.PROFILE_OPENED, String.format("%s %s", item.name, item.surname));

            if (twoPane) {
                val fragment = EmployeeDetailFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(EmployeeDetailFragment.ARG_ITEM_ID, item)
                    }
                }
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(v.context, EmployeeDetailActivity::class.java).apply {
                    putExtra(EmployeeDetailFragment.ARG_ITEM_ID, item)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.employee_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.imageView.setImageBitmap(null)
        Glide.with(holder.imageView)
            .asBitmap()
            .load(item.getImageUrl())
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {

                    val bitmap = Bitmap.createBitmap(resource, 0, 0, resource.height, resource.height)
                    holder.imageView.setImageBitmap(bitmap)

                }
                override fun onLoadCleared(placeholder: Drawable?) {

                }
            })

        holder.nameTextView.text = String.format("%s %s", item.name, item.surname)
        holder.titleTextView.text = item.title

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }

        //track event for presenting employee to list
        EventService.sendEvent(EventType.EMPLOYEE_SHOWED_IN_LIST, String.format("%s %s", item.name, item.surname))

    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: CircleImageView = view.findViewById(R.id.image)
        val nameTextView: TextView = view.findViewById(R.id.nameTV)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
    }

    fun setItems(items: List<Employee>) {
        this.values = items
    }
}