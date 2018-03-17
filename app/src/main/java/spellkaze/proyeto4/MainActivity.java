package spellkaze.proyeto4;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText textField;

    Button buttonSubmit;
    Button buttonDeleteAll;

    ListView listView;

    DataHolder dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textField = (EditText) findViewById(R.id.editText);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonDeleteAll = (Button) findViewById(R.id.buttonDeleteAll);
        listView = (ListView) findViewById(R.id.listView);

        buttonSubmit.setOnClickListener(ButtonSubmit);
        buttonDeleteAll.setOnClickListener(ButtonDeleteAll);
        listView.setOnItemClickListener(SelectItemOnList);

        dataHolder = DataHolder.getInstance();

    }
    private final AdapterView.OnItemClickListener SelectItemOnList = new AdapterView.OnItemClickListener()
    {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            final int position = i;
            Course courseObtained = dataHolder.getCourseList(position);
            Log.d("ClickItem", "Course clicked: " + courseObtained);
            String textMainCourse = courseObtained.getMainCourse() + " ";
            String textPrerequisite = android.text.TextUtils.join(" ", courseObtained.getPreRequisiteCourseList());

            String result = textMainCourse += textPrerequisite;
            textField.setText(result);

        }
    };

    private final View.OnClickListener ButtonSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String submitText = textField.getText().toString();
            Course courseSubmitted = SubmitCourse(submitText);

            if(courseSubmitted != null && VerifyRepetitions(courseSubmitted.getMainCourse()))
            {
                dataHolder.addCourseList(SubmitCourse(submitText));
                Log.d("Submit", courseSubmitted.toString());
            }
            else
            {
                Log.d("Submit", "AbortandoSubmit");
            }

            if(courseSubmitted != null && !VerifyRepetitions(courseSubmitted.getMainCourse()))
            {
                dataHolder.editList(courseSubmitted);
            }

            UpdateListView();
        }
    };

    private final View.OnClickListener ButtonDeleteAll = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dataHolder.clearCourseList();
            UpdateListView();
        }
    };

    private Course SubmitCourse(String string) //Entrar curso
    {
        if(!(string.length()>0))
        {
            Log.d("Button", "Nothing entered");
            return null;
        }

        String[] separarString = string.split(" ");
        String mainCourse = separarString[0];

        ArrayList<String> preRequisite = new ArrayList<>();

        for(int i = 1; i< separarString.length; i++)
        {
            preRequisite.add(separarString[i]);
        }

        return new Course(mainCourse, preRequisite);
    }


    public void UpdateListView() //Actualizar lissta
    {
        AdapterCourseList adapterCourseList = new AdapterCourseList(this, dataHolder.getCourseList());
        Log.d("Updatelist", "Mandando lista: " + dataHolder.getCourseList());
        listView.setAdapter(adapterCourseList);
    }

    private boolean VerifyRepetitions(String mainCourse) //Verificar reperticion de Main
    {
        for(Course course : dataHolder.getCourseList())
        {
            if(mainCourse.equals(course.getMainCourse()))
            {
                return false; //Se han encontrado coincidencias
            }
        }
        return true; //No hay coincidencias
    }

}
