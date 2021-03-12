package com.echoeyecodes.mia.fragments.dialogframents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.echoeyecodes.mia.R
import com.echoeyecodes.mia.adapters.NoteAdapter
import com.echoeyecodes.mia.adapters.TodoAdapter
import com.echoeyecodes.mia.utils.CustomItemDecoration
import com.echoeyecodes.mia.utils.DefaultItemCallBack

class TodoFragment : Fragment() {

    private lateinit var recyclerView:RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_todo, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        val itemDecoration = CustomItemDecoration(10)

        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(itemDecoration)

        val adapter = TodoAdapter(DefaultItemCallBack())
        recyclerView.adapter = adapter
    }
}