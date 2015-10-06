package com.kyf.laoyou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kyf.laoyou.R;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kyf on 2015/10/1.
 */
public class ActivitylistAdapter extends BaseAdapter {

    private Context mContext;

    private List<Map<String, Object>> dataset;

    public ActivitylistAdapter(Context context, List<Map<String, Object>> ds){
        mContext = context;
        dataset = ds;
    }

    @Override
    public int getCount(){
        return dataset.size();
    }

    @Override
    public Object getItem(int position){
        if(position < 0 || position > (getCount() - 1)) return null;
        return dataset.get(position);
    }

    @Override
    public long getItemId(int position){
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View result;
        TextView activity_list_title, activity_list_creater, activity_list_pay, activity_list_addr, activity_list_date, activity_list_note, activity_list_number, activity_list_share, activity_list_join;
        CircleImageView activity_list_photo;
        MessageHolder messageHolder;

        if(convertView != null){
            result = convertView;
            messageHolder = (MessageHolder) result.getTag();
            activity_list_title = messageHolder.activity_list_title;
            activity_list_creater = messageHolder.activity_list_creater;
            activity_list_pay = messageHolder.activity_list_pay;
            activity_list_addr = messageHolder.activity_list_addr;
            activity_list_date = messageHolder.activity_list_date;
            activity_list_note = messageHolder.activity_list_note;
            activity_list_number = messageHolder.activity_list_number;
            activity_list_share = messageHolder.activity_list_share;
            activity_list_join = messageHolder.activity_list_join;
            activity_list_photo = messageHolder.activity_list_photo;
        }else{
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            result = inflater.inflate(R.layout.activitylist, null);
            messageHolder = new MessageHolder();
            activity_list_title = (TextView) result.findViewById(R.id.activity_list_title);
            activity_list_creater = (TextView) result.findViewById(R.id.activity_list_creater);
            activity_list_pay = (TextView) result.findViewById(R.id.activity_list_pay);
            activity_list_addr = (TextView) result.findViewById(R.id.activity_list_addr);
            activity_list_date = (TextView) result.findViewById(R.id.activity_list_date);
            activity_list_note = (TextView) result.findViewById(R.id.activity_list_note);
            activity_list_number = (TextView) result.findViewById(R.id.activity_list_number);
            activity_list_share = (TextView) result.findViewById(R.id.activity_list_share);
            activity_list_join = (TextView) result.findViewById(R.id.activity_list_join);
            activity_list_photo = (CircleImageView) result.findViewById(R.id.activity_list_photo);
            messageHolder.activity_list_title = activity_list_title;
            messageHolder.activity_list_creater = activity_list_creater;
            messageHolder.activity_list_pay = activity_list_pay;
            messageHolder.activity_list_addr = activity_list_addr;
            messageHolder.activity_list_date = activity_list_date;
            messageHolder.activity_list_note = activity_list_note;
            messageHolder.activity_list_number = activity_list_number;
            messageHolder.activity_list_share = activity_list_share;
            messageHolder.activity_list_join = activity_list_join;
            messageHolder.activity_list_photo = activity_list_photo;
            result.setTag(messageHolder);
        }

        Map<String, Object> current = dataset.get(position);
        String title = current.get("title").toString();
        int photo = (int) current.get("photo");
        String creater = current.get("creater").toString();
        String pay = current.get("pay").toString();
        String addr = current.get("addr").toString();
        String date = current.get("date").toString();
        String note = current.get("note").toString();
        String number = current.get("number").toString();

        activity_list_title.setText(title);
        activity_list_photo.setImageResource(photo);
        activity_list_creater.setText(creater);
        activity_list_pay.setText(pay);
        activity_list_addr.setText(addr);
        activity_list_date.setText(date);
        activity_list_note.setText(note);
        activity_list_number.setText(number);
        return result;
    }


    public class MessageHolder{

        public CircleImageView activity_list_photo;

        public TextView activity_list_title, activity_list_creater, activity_list_pay, activity_list_addr, activity_list_date, activity_list_note, activity_list_number, activity_list_share, activity_list_join;
    }
}
