package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aston.cdnt4.R;

public class MyAdapter extends BaseAdapter {

    public MyAdapter(Context mContext, LayoutInflater mLayoutInflater) {
        this.mContext = mContext;
        this.mLayoutInflater = mLayoutInflater;
    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // viewHolder optimise la mémoire lors du chargement de la liste
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.adapter_line, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.adapter_line_textView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText("" + position);
        //Log.d("TAG", "getView:" + position);
        return convertView;
    }

    private class ViewHolder {
        TextView textView;
    }
}
