package com.echoeyecodes.mia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.echoeyecodes.jinx.interfaces.CreateTaskFragmentInterface
import com.echoeyecodes.jinx.models.TaskDateModel
import com.echoeyecodes.jinx.models.TaskTimeModel
import com.echoeyecodes.mia.fragments.bottomsheets.CreateTodoDialogFragment
import com.echoeyecodes.mia.fragments.tabfragments.NoteFragment
import com.echoeyecodes.mia.fragments.tabfragments.PomodoroFragment
import com.echoeyecodes.mia.fragments.tabfragments.TodoFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), CreateTaskFragmentInterface {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout:TabLayout
    private lateinit var addBtn:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)
        addBtn = findViewById(R.id.add_btn)


        viewPager.offscreenPageLimit = 3
        viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            when(position){
                0 -> tab.text = "NOTES"
                1 -> tab.text = "TODO"
                2 -> tab.text = "POMODORO"
            }
        }.attach()

        addBtn.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            val frag = supportFragmentManager.findFragmentByTag("CREATE_TODO_DIALOG_FRAGMENT")
            if(frag != null){
                transaction.remove(frag)
            }
            transaction.addToBackStack(null)
            val fragment = CreateTodoDialogFragment.newInstance()
            fragment.show(transaction, "CREATE_TODO_DIALOG_FRAGMENT")
        }
    }

    private class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){

        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> NoteFragment()
                1 -> TodoFragment()
                else -> PomodoroFragment()
            }
        }
    }

    private fun getTaskFragment() : CreateTodoDialogFragment?{
        return supportFragmentManager.findFragmentByTag(CreateTodoDialogFragment.TAG) as CreateTodoDialogFragment?
    }

    override fun openDateTimeDialog() {
        getTaskFragment()?.openDateTimeDialog()
    }

    override fun onDateSelected(date: TaskDateModel) {
        getTaskFragment()?.onDateSelected(date)
    }

    override fun onTimeSelected(time: TaskTimeModel) {
        getTaskFragment()?.onTimeSelected(time)
    }

    override fun setDateType(idx: Int) {
        getTaskFragment()?.setDateType(idx)
    }

    override fun onDefaultDateTimeSelected(date: TaskDateModel, time: TaskTimeModel) {
        getTaskFragment()?.onDefaultDateTimeSelected(date, time)
    }

    override fun setIgnoreDateTime(value: Boolean) {
        getTaskFragment()?.setIgnoreDateTime(value)
    }
}