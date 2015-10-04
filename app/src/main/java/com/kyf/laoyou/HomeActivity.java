package com.kyf.laoyou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.Inflater;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.kyf.laoyou.adapter.ChatlistAdapter;
import com.kyf.laoyou.adapter.ContactListAdapter;

public class HomeActivity extends BaseActivity implements View.OnClickListener, OnItemClickListener {

    private static final String LogTag = "HomeActivity";

    private Context mContext;

    SectionsPagerAdapter mSectionsPagerAdapter;

    private PopupWindow pw;

    ViewPager mViewPager;

    private ImageView tab_playground_img, tab_contact_img, tab_activity_img, tab_me_img;

    private TextView tab_playground_text, tab_contact_text, tab_activity_text, tab_me_text;

    private LinearLayout tab_playground, tab_contact, tab_activity, tab_me;

    private List<Map<String, Integer>> tabControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLayout = R.layout.activity_home;
        super.onCreate(savedInstanceState);
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

        initActionBar();
    }

    private void initActionBar(){
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.menu_home);
        View actionView = actionBar.getCustomView();
        ImageView morebt = (ImageView) findViewById(R.id.home_code_more_bt);
        morebt.setOnClickListener(this);
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

        initTabControls();

        //需要完善基础信息才能使用相关功能
        String title = "提示";
        String content = "请先完善相关信息才能使用全部功能";
        alertView = new AlertView(title, content, null, new String[]{"填写信息"}, new String[]{"先看看"}, mContext, AlertView.Style.Alert, this);
        alertView.show();
    }

    @Override
    public void onItemClick(Object obj, int position){
        if(position == 0) {
            Toast.makeText(MyApplication.getContext(), "button " + position, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.menu_visit_bt:{
                Intent intent = new Intent(this, VisitActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.home_code_more_bt:{
                if(pw == null){
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View menu_home = inflater.inflate(R.layout.menu_home_list, null);
                    pw = new PopupWindow(menu_home, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    pw.setBackgroundDrawable(new BitmapDrawable());
                    pw.showAsDropDown(view);
                    TextView menu_visit_bt = (TextView) menu_home.findViewById(R.id.menu_visit_bt);
                    menu_visit_bt.setOnClickListener(this);
                }else{
                    if(pw.isShowing()){
                        pw.dismiss();
                    }else{
                        pw.showAsDropDown(view);
                    }
                }
                break;
            }
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

            PlaceholderFragment placeholderFragment =  PlaceholderFragment.newInstance(position + 1);
            return placeholderFragment;
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
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private PlaceholderFragment mContext;

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
            mContext = this;
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

        @Override
        public void onClick(View view){
            int id = view.getId();
            switch(id){
                case R.id.VisitBt:{
                    Intent intent = new Intent(mContext.getActivity(), VisitActivity.class);
                    mContext.getActivity().startActivity(intent);
                    break;
                }
            }
        }

        private void bindViewEvent(View rootView, int position){
            switch(position){
                case 1:{
                    ListView chatlist = (ListView) rootView.findViewById(R.id.chatlist);
                    List<Map<String, String>> ds = new ArrayList<Map<String, String>>();
                    chatlist.setVerticalScrollBarEnabled(false);

                    Map<String, String> msg1 = new HashMap<String, String>();
                    msg1.put("message", mContext.getActivity().getResources().getString(R.string.message_welcome));
                    ds.add(msg1);

                    Map<String, String> msg2 = new HashMap<String, String>();
                    msg2.put("message", mContext.getActivity().getResources().getString(R.string.message_boss_complete_info));
                    ds.add(msg2);

                    Map<String, String> msg3 = new HashMap<String, String>();
                    msg3.put("message", mContext.getActivity().getResources().getString(R.string.message_welcome));
                    ds.add(msg3);

                    Map<String, String> msg4 = new HashMap<String, String>();
                    msg4.put("message", mContext.getActivity().getResources().getString(R.string.message_boss_complete_info));
                    ds.add(msg4);

                    ChatlistAdapter chatlistAdapter = new ChatlistAdapter(mContext.getActivity(), ds);
                    chatlist.setAdapter(chatlistAdapter);
                    chatlist.setDividerHeight(0);
                    break;
                }
                case 2:{
                    LinearLayout ContactList = (LinearLayout) rootView.findViewById(R.id.ContactList);
                    LinearLayout ContactNo = (LinearLayout) rootView.findViewById(R.id.ContactNo);
                    ListView ContactListView = (ListView) rootView.findViewById(R.id.ContactListView);
                    Button VisitBt = (Button) rootView.findViewById(R.id.VisitBt);

                    ContactListView.setVerticalScrollBarEnabled(false);

                    List<Map<String, Object>> PersonList = new ArrayList<Map<String, Object>>();

                    String[] names = {"刘德华", "张学友", "黎明", "郭富城", "黄继光"};
                    String[] phones = {"15810809554", "13825633652", "13965254124", "13644525425", "1396535652"};
                    Integer[] photoes = {R.mipmap.andy1, R.mipmap.andy2, R.mipmap.andy3, R.mipmap.andy4, R.mipmap.andy5};

                    for(int i = 0; i < 20; i++) {
                        Map<String, Object> item1 = new HashMap<String, Object>();
                        item1.put("nickname", names[i%names.length]);
                        item1.put("photo", photoes[i%names.length]);
                        item1.put("phone", phones[i%names.length]);
                        PersonList.add(item1);
                    }


                    if(PersonList.size() == 0){
                        ContactNo.setVisibility(View.VISIBLE);
                        ContactList.setVisibility(View.INVISIBLE);
                        VisitBt.setOnClickListener(this);
                    }else{
                        ContactNo.setVisibility(View.INVISIBLE);
                        ContactList.setVisibility(View.VISIBLE);
                        ContactListAdapter adapter = new ContactListAdapter(mContext.getActivity(), PersonList);
                        ContactListView.setAdapter(adapter);
                    }

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
