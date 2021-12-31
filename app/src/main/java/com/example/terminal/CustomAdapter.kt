package com.example.terminal


import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(private val dataSet: ArrayList<studentInfo>, context: MainActivity) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val std_name: TextView
        val regNo : TextView
        val contactNo : TextView
        val SMSBtn : Button


        init {
            // Define click listener for the ViewHolder's View.
            std_name = view.findViewById(R.id.stdName)
            regNo = view.findViewById(R.id.regNo)
            contactNo = view.findViewById(R.id.contact)
            SMSBtn = view.findViewById(R.id.smsBtn)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.single_layout_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val item = dataSet[position]

        viewHolder.std_name.text = item.name
        viewHolder.regNo.text = item.RegNo
        viewHolder.contactNo.text = item.RegNo

        viewHolder.SMSBtn.setOnClickListener{view ->
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(viewHolder.contactNo.toString(), null, "This is message body.", null, null)

        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}