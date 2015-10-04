package com.kyf.laoyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyf.laoyou.R;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kyf on 2015/10/1.
 */
public class ContactListAdapter extends BaseAdapter implements View.OnClickListener {

    private static final String LogTag = "ContactListAdapter";

    private Context mContext;

    private List<Map<String, Object>> dataset;

    public ContactListAdapter(Context context, List<Map<String, Object>> ds){
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
        TextView contactlist_nickname, contactlist_phone;
        CircleImageView contactlist_photo;
        ImageView call_phone_bt;
        ContactHolder contactHolder;

        if(convertView != null){
            result = convertView;
            contactHolder = (ContactHolder) result.getTag();
            contactlist_phone = contactHolder.contactlist_phone;
            contactlist_nickname = contactHolder.contactlist_nickname;
            contactlist_photo = contactHolder.contactlist_photo;
            call_phone_bt = contactHolder.call_phone_bt;
        }else{
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            result = inflater.inflate(R.layout.contactlist, null);
            contactHolder = new ContactHolder();
            contactlist_nickname = (TextView) result.findViewById(R.id.contactlist_nickname);
            contactlist_phone = (TextView) result.findViewById(R.id.contactlist_phone);
            contactlist_photo = (CircleImageView) result.findViewById(R.id.contactlist_photo);
            call_phone_bt = (ImageView) result.findViewById(R.id.call_phone_bt);
            contactHolder.contactlist_nickname = contactlist_nickname;
            contactHolder.contactlist_phone = contactlist_phone;
            contactHolder.contactlist_photo = contactlist_photo;
            contactHolder.call_phone_bt = call_phone_bt;
            result.setTag(contactHolder);
        }

        Map<String, Object> current = dataset.get(position);
        String nickname = current.get("nickname").toString();
        String phone = current.get("phone").toString();
        int  photo_res = (int) current.get("photo");
        contactlist_nickname.setText(nickname);
        contactlist_photo.setImageResource(photo_res);
        contactlist_phone.setText(phone);
        call_phone_bt.setTag(phone);
        call_phone_bt.setOnClickListener(this);
        return result;
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.call_phone_bt:{
                String phoneno = view.getTag().toString();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneno));
                mContext.startActivity(intent);
                break;
            }
        }
    }


    public class ContactHolder{

        public TextView contactlist_nickname, contactlist_phone;

        private CircleImageView contactlist_photo;

        private ImageView call_phone_bt;
    }
}
