package com.echoeyecodes.jinx.fragments.BottomSheetFragments

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.jinx.adapters.MenuBottomSheetAdapter
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.DefaultItemCallBack

class MenuFragment : BottomSheetDialogFragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.fragment_menu_recycler_view)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        val adapter = MenuBottomSheetAdapter(DefaultItemCallBack(), requireContext())
        recyclerView.adapter = adapter
    }


    companion object {
        fun newInstance(): MenuFragment = MenuFragment()
    }
}