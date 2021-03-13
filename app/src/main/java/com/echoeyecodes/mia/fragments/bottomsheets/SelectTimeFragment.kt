package com.echoeyecodes.jinx.fragments.BottomSheetFragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.jinx.adapters.SelectTimeFragmentAdapter
import com.echoeyecodes.jinx.interfaces.CreateTaskFragmentInterface
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.DefaultItemCallBack
import java.util.*

class SelectTimeFragment(private val fragmentListener:CreateTaskFragmentInterface) : BottomSheetDialogFragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_date_bottomsheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.date_bottomsheet_recycler_view)

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        val adapter = SelectTimeFragmentAdapter(DefaultItemCallBack(), requireContext(), fragmentListener)
        recyclerView.adapter = adapter
    }


    companion object {
        fun newInstance(fragmentListener:CreateTaskFragmentInterface): SelectTimeFragment = SelectTimeFragment(fragmentListener)
    }


}