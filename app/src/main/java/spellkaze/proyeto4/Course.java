package spellkaze.proyeto4;

import java.util.ArrayList;

/**
 * Created by Spellkaze on 16/03/2018.
 */

public class Course {
    String mainCourse;
    ArrayList<String> preRequisiteCourses;

    public Course(String mainCourse, ArrayList<String> preRequisiteCourses)
    {
        this.mainCourse = mainCourse;
        this.preRequisiteCourses = preRequisiteCourses;
    }

    public String getMainCourse()
    {
        return mainCourse;
    }

    public ArrayList<String> getPreRequisiteCourseList()
    {
        return preRequisiteCourses;
    }

    public String showPreRequisiteCourseList()
    {
        if(preRequisiteCourses.size()>0)
        {
            return preRequisiteCourses.toString();
        }
        else
        return "";
    }

    public void changePreRequisiteCourses(ArrayList<String> preRequisiteCourses)
    {
        this.preRequisiteCourses = preRequisiteCourses;
    }

    @Override
    public String toString()
    {
        String returnString = mainCourse + " " + preRequisiteCourses;
        return returnString;
    }


}
