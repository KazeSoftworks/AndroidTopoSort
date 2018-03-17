package spellkaze.proyeto4;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Spellkaze on 16/03/2018.
 */

public class AdapterCourseList extends BaseAdapter{

    protected Activity activity;
    protected ArrayList<Course> listCourse;
    DataHolder dataHolder;

    public AdapterCourseList(Activity activity, ArrayList<Course> listCourse)
    {
        this.activity = activity;
        this.listCourse = listCourse;
        dataHolder = DataHolder.getInstance();
    }

    @Override
    public int getCount() {
        return listCourse.size();
    }

    public void clear()
    {
        listCourse.clear();
    }

    public void addAll(ArrayList<Course> courses)
    {
        for(int i = 0; i <courses.size(); i++)
        {
            listCourse.add(courses.get(i));
        }
    }

    @Override
    public Object getItem(int i) {
        return listCourse.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View customView = view;
        final int position = i;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            customView= inf.inflate(R.layout.custom_list_layout, null);
        }

        Course course = listCourse.get(position);

        TextView mainCourseText = (TextView) customView.findViewById(R.id.mainCourse);
        mainCourseText.setText(course.getMainCourse());

        TextView preRequisteText = (TextView) customView.findViewById(R.id.coursePrerequisite);
        preRequisteText.setText(course.showPreRequisiteCourseList());


        Button buttonDelete = (Button) customView.findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHolder.removeCourseList(position);
                ((MainActivity)activity).UpdateListView();
            }
        });

        return customView;

    }
}
