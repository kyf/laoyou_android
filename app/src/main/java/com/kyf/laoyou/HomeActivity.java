package com.kyf.laoyou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LogTag = "HomeActivity";

    private Context mContext;

    SectionsPagerAdapter mSectionsPagerAdapter;

    ViewPager mViewPager;

    private ImageView tab_playground_img, tab_contact_img, tab_activity_img, tab_me_img;

    private TextView tab_playground_text, tab_contact_text, tab_activity_text, tab_me_text;

    private LinearLayout tab_playground, tab_contact, tab_activity, tab_me;

    private List<Map<String, Integer>> tabControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;
        String visitCode = getIntent().getStringExtra("visitCode");

        Toast.makeText(HomeActivity.this, "邀请码：" + visitCode, Toast.LENGTH_SHORT).show();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                changeTab(position);
            }
        });
        mViewPager.setAdapter(mSectionsPagerAdapter);

        initView();
    }

    private void initView(){
        tab_playground_img = (ImageView) findViewById(R.id.tab_playground_img);
        tab_contact_img = (ImageView) findViewById(R.id.tab_contact_img);
        tab_activity_img = (ImageView) findViewById(R.id.tab_activity_img);
        tab_me_img = (ImageView) findViewById(R.id.tab_me_img);

        tab_playground_text = (TextView) findViewById(R.id.tab_playground_text);
        tab_contact_text = (TextView) findViewById(R.id.tab_contact_text);
        tab_activity_text = (TextView) findViewById(R.id.tab_activity_text);
        tab_me_text = (TextView) findViewById(R.id.tab_me_text);

        tab_playground = (LinearLayout) findViewById(R.id.tab_playground);
        tab_contact = (LinearLayout) findViewById(R.id.tab_contact);
        tab_activity = (LinearLayout) findViewById(R.id.tab_activity);
        tab_me = (LinearLayout) findViewById(R.id.tab_me);

        tab_playground.setOnClickListener(this);
        tab_contact.setOnClickListener(this);
        tab_activity.setOnClickListener(this);
        tab_me.setOnClickListener(this);

        tab_playground_text.setOnClickListener(this);
        tab_contact_text.setOnClickListener(this);
        tab_activity_text.setOnClickListener(this);
        tab_me_text.setOnClickListener(this);

        initTabControls();
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.tab_playground:
                mViewPager.setCurrentItem(0, true);
                changeTab(0);
                break;
            case R.id.tab_contact:
                mViewPager.setCurrentItem(1, true);
                changeTab(1);
                break;
            case R.id.tab_activity:
                mViewPager.setCurrentItem(2, true);
                changeTab(2);
                break;
            case R.id.tab_me:
                mViewPager.setCurrentItem(3, true);
                changeTab(3);
                break;
        }
    }

    private void initTabControls(){
        tabControls = new ArrayList<Map<String, Integer>>();
        Map<String, Integer> control1 = new HashMap<String, Integer>();
        control1.put("img", R.id.tab_playground_img);
        control1.put("text", R.id.tab_playground_text);
        control1.put("src", R.mipmap.playground);
        control1.put("src1", R.mipmap.playground_1);
        tabControls.add(control1);

        Map<String, Integer> control2 = new HashMap<String, Integer>();
        control2.put("img", R.id.tab_contact_img);
        control2.put("text", R.id.tab_contact_text);
        control2.put("src", R.mipmap.contact);
        control2.put("src1", R.mipmap.contact_1);
        tabControls.add(control2);

        Map<String, Integer> control3 = new HashMap<String, Integer>();
        control3.put("img", R.id.tab_activity_img);
        control3.put("text", R.id.tab_activity_text);
        control3.put("src", R.mipmap.activity);
        control3.put("src1", R.mipmap.activity_1);
        tabControls.add(control3);

        Map<String, Integer> control4 = new HashMap<String, Integer>();
        control4.put("img", R.id.tab_me_img);
        control4.put("text", R.id.tab_me_text);
        control4.put("src", R.mipmap.me);
        control4.put("src1", R.mipmap.me_1);
        tabControls.add(control4);
    }

    private void changeTab(int position){
        int index = 0;
        for(Map<String, Integer> it : tabControls){
            ImageView iv = (ImageView) findViewById(it.get("img"));
            TextView tv = (TextView) findViewById(it.get("text"));

            if(index == position){
                iv.setImageResource(it.get("src"));
                tv.setTextColor(getResources().getColor(R.color.bottom_menu_text_color_on));
            }else{
                iv.setImageResource(it.get("src1"));
                tv.setTextColor(getResources().getColor(R.color.bottom_menu_text_color_off));
            }
            index++;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Bundle args = getArguments();
            int layout = R.layout.fragment_home;
            switch(args.getInt(ARG_SECTION_NUMBER)){
                case 2:
                    layout = R.layout.fragment_home_contact;
                    break;
                default:
            }

            View rootView = inflater.inflate(layout, container, false);

            bindViewEvent(rootView, args.getInt(ARG_SECTION_NUMBER));
            return rootView;
        }

        private void bindViewEvent(View rootView, int position){
            switch(position){
                case 2:
                case 1:{
                    TextView section_label = (TextView) rootView.findViewById(R.id.section_label);
                    if(section_label != null) {
                        Log.e(LogTag, "bindViewEvent" + position);
                        section_label.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MyApplication.getContext(), "section_label 121212121", Toast.LENGTH_SHORT);
                            }
                        });
                    }
                    break;
                }
                case 21:{

                    break;
                }
                case 3:{

                    break;
                }
                case 4:{

                    break;
                }
            }
        }
    }

}
