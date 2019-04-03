package ir.ipack.ehsan.local.ipack.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.mydata.MyDataFragment
import ir.ipack.ehsan.local.ipack.myplan.MyPlanFragment
import ir.ipack.ehsan.local.ipack.mytalk.MyTalkFragment
import ir.ipack.ehsan.local.ipack.mytext.MyTextFragment
import ir.ipack.ehsan.local.ipack.utils.FontCache
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import java.util.Arrays

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    private lateinit var mTabImages: List<ImageView>
    private lateinit var mPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mTabImages = Arrays.asList(plan_tab_image, data_tab_image, talk_tab_image/*, text_tab_image*/)
        mPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        mPagerAdapter.addTabFragment(TabFragment(getString(R.string.my_plan), MyPlanFragment()))
        mPagerAdapter.addTabFragment(TabFragment(getString(R.string.data), MyDataFragment()))
        mPagerAdapter.addTabFragment(TabFragment(getString(R.string.talk), MyTalkFragment()))
        mPagerAdapter.addTabFragment(TabFragment(getString(R.string.text), MyTextFragment()))
        view_pager.adapter = mPagerAdapter
        view_pager.offscreenPageLimit = 3

        tab_layout.setupWithViewPager(view_pager)
        tab_layout.setOnTabSelectedListener(this)

        updateTabText(mPagerAdapter)
    }

    private fun updateTabText(mPagerAdapter: ViewPagerAdapter) {
        val font = FontCache.get("Roboto-Regular.ttf", this)
        for (i in 0 until mPagerAdapter.count) {
            val tv = ((((tab_layout.getChildAt(0) as LinearLayout).getChildAt(i)) as LinearLayout).getChildAt(1)) as TextView
            tv.isAllCaps = false
            tv.typeface = font
        }
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
    }

    companion object {
        private class TabFragment(val title: String, val fragment: Fragment)
        private class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
            private val fragmentList = ArrayList<TabFragment>()
            override fun getItem(position: Int): Fragment = fragmentList[position].fragment
            override fun getCount(): Int = fragmentList.size
            fun addTabFragment(tabFragment: TabFragment) = fragmentList.add(tabFragment)
            override fun getPageTitle(position: Int): CharSequence? = fragmentList[position].title
        }
    }
}