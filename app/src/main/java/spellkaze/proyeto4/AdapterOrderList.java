package spellkaze.proyeto4;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Spellkaze on 17/03/2018.
 */

public class AdapterOrderList extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<String> resultList;

    public AdapterOrderList(Activity activity,ArrayList <String> resultList)
    {
        this.activity = activity;
        this.resultList = resultList;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public Object getItem(int i) {
        return resultList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View customView = view;
        int position = i;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            customView = inf.inflate(R.layout.custom_list_order_layout, null);
        }

        String coursePerSemester = resultList.get(position);

        TextView semesterText = (TextView) customView.findViewById(R.id.numberSemester);
        semesterText.setText(String.valueOf(position));

        TextView courseText = (TextView) customView.findViewById(R.id.courseToTake);
        courseText.setText(coursePerSemester);

        return customView;
    }

}
