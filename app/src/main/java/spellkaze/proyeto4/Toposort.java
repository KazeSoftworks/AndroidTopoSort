package spellkaze.proyeto4;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Spellkaze on 17/03/2018.
 */

public class Toposort {
    private ArrayList<Course> coursesToSort = new ArrayList<>();
    private ArrayList<String> result = new ArrayList<>();

    public void setCoursesToSort(ArrayList<Course> coursesToSort)
    {
        this.coursesToSort = coursesToSort;
    }

    public boolean verifyCoursesToSort()
    {
        if(coursesToSort.size()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public ArrayList<String> getResult()
    {
        return result;
    }

    public void TopoSorting()
    {
        ArrayList<ArrayList<String>> rawPrerequisiteList = new ArrayList<>();
        ArrayList<String> nonPrerequisiteList = new ArrayList<>();
        ArrayList<String> cyclePrerequisiteList = new ArrayList<>();
        ArrayList<String> mainCourseList = new ArrayList<>();

        for(Course course : coursesToSort)
        {
            mainCourseList.add(course.getMainCourse());
            rawPrerequisiteList.add(course.getPreRequisiteCourseList());
        }


        for(ArrayList<String> list : rawPrerequisiteList) //Cursos sin prerequisito
        {
            for(String requisite : list)
            {
                if(mainCourseList.contains(requisite))
                {
                    Log.d("Contain","main course list contains " + requisite);
                    continue;
                }
                if(!nonPrerequisiteList.contains(requisite))
                {
                    nonPrerequisiteList.add(requisite);
                }

            }
        }

        result.add(nonPrerequisiteList.toString());
        cyclePrerequisiteList = nonPrerequisiteList;

        Log.d("TopoSorting", mainCourseList.toString());
        Log.d("TopoSorting", rawPrerequisiteList.toString());
        Log.d("TopoSorting", cyclePrerequisiteList.toString());

         /*int size = mainCourseList.size();
        while(size>0)
        {
            ArrayList<String>courseComplyPrerequisiteList = new ArrayList<>();
            for(Course course : coursesToSort)
            {
                if(course.getPreRequisiteCourseList().containsAll(cyclePrerequisiteList))
                {
                    //Cumple prerequisito
                    courseComplyPrerequisiteList.add(course.getMainCourse());
                    size--;
                }
            }
            if(courseComplyPrerequisiteList.size()>0)
            {
                result.add(courseComplyPrerequisiteList.toString());
                cyclePrerequisiteList.addAll(courseComplyPrerequisiteList);
            }
        }
        Log.d("TopoSortingRESULT", result.toString());
        */
        ArrayList<Course> checkCourse = new ArrayList<>();
        checkCourse.addAll(coursesToSort);
        Log.d("Course aproved", cyclePrerequisiteList.toString());
         while(true)
         {
             ArrayList<Integer> toDelete = new ArrayList<>();
             ArrayList<String> ongoingCourses = new ArrayList<>();

             Log.d("Course on list", checkCourse.toString());
             for(int i = 0; i<checkCourse.size(); i++)
             {
                 /*if(checkCourse.get(i).getPreRequisiteCourseList().containsAll(cyclePrerequisiteList))
                 {
                     toDelete.add(i);
                     ongoingCourses.add(checkCourse.get(i).getMainCourse());
                     Log.d("Deleting", checkCourse.get(i).getMainCourse());
                 }*/

                 if(cyclePrerequisiteList.containsAll(checkCourse.get(i).getPreRequisiteCourseList()))
                 {
                     toDelete.add(i);

                     ongoingCourses.add(checkCourse.get(i).getMainCourse());
                     Log.d("Deleting", checkCourse.get(i).getMainCourse() + " POS: " + i);
                 }

             }


             for(int i = toDelete.size()-1; i>=0; i--)
             {
                 int index = toDelete.get(i);
                 checkCourse.remove(index);

                 Log.d("TopoSortingCheckCourse", checkCourse.toString());

             }
             if(!(toDelete.size()>0))
             {
                 break;
             }

             cyclePrerequisiteList.addAll(ongoingCourses);
             Log.d("TopoSortingRE", cyclePrerequisiteList.toString());
             result.add(ongoingCourses.toString());
             Log.d("TopoSortingRESULT", result.toString());
             Log.d("tO DELTE", toDelete.toString());
         }

    }

}
