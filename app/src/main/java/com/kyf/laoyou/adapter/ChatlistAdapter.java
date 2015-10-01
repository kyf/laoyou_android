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

/**
 * Created by kyf on 2015/10/1.
 */
public class ChatlistAdapter extends BaseAdapter {

    private Context mContext;

    private List<Map<String, String>> dataset;

    public ChatlistAdapter(Context context, List<Map<String, String>> ds){
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
        TextView msgText;
        MessageHolder messageHolder;

        if(convertView != null){
            result = convertView;
            messageHolder = (MessageHolder) result.getTag();
            msgText = messageHolder.msgText;
        }else{
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            result = inflater.inflate(R.layout.chatlist, null);
            messageHolder = new MessageHolder();
            msgText = (TextView) result.findViewById(R.id.msgText);
            messageHolder.msgText = msgText;
            result.setTag(messageHolder);
        }

        Map<String, String> current = dataset.get(position);
        String msg = current.get("message");
        msgText.setText(msg);
        return result;
    }


    public class MessageHolder{
        public TextView msgText;
    }
}
