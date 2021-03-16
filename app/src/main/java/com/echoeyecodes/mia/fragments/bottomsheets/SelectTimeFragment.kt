package com.echoeyecodes.mia.fragments.bottomsheets


import android.content.Context
import android.os.Bundle
import android.view.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.jinx.adapters.SelectTimeFragmentAdapter
import com.echoeyecodes.jinx.interfaces.CreateTaskFragmentInterface
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.utils.DefaultItemCallBack

class SelectTimeFragment() : BottomSheetDialogFragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fragmentListener: CreateTaskFragmentInterface

    companion object{
        const val TAG = "SELECT_TIME_FRAGMENT"
        fun newInstance() = SelectTimeFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentListener = context as CreateTaskFragmentInterface
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_date_bottomsheet, container, false)

        recyclerView = view.findViewById(R.id.date_bottomsheet_recycler_view)

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        val adapter = SelectTimeFragmentAdapter(DefaultItemCallBack(), requireContext(), fragmentListener)
        recyclerView.adapter = adapter

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetStyle)
    }
}