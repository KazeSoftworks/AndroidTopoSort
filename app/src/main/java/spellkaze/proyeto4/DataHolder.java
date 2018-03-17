package spellkaze.proyeto4;

import java.util.ArrayList;

/**
 * Created by Spellkaze on 16/03/2018.
 */

public class DataHolder {
    private ArrayList<Course> courseList = new ArrayList<>();

    private static DataHolder instance;

    private DataHolder()
    {
        //Bloquear instancias
    }

    public static DataHolder getInstance()
    {
        if(instance == null)
        {
           instance = new DataHolder();
        }
        return instance;
    }

    public void addCourseList (Course course)
    {
        courseList.add(course);
    }

    public ArrayList<Course> getCourseList()
    {
        return courseList;
    }

    public Course getCourseList(int i)
    {
        return courseList.get(i);
    }

    public void clearCourseList()
    {
        courseList.clear();
    }

    public void removeCourseList(int i)
    {
        courseList.remove(i);
    }

    public void editList(Course course)
    {
        for(Course courseOnList: courseList)
        {
            if(courseOnList.getMainCourse().equals(course.getMainCourse()))
            {
                courseOnList.changePreRequisiteCourses(course.getPreRequisiteCourseList());
            }
        }
    }
}
