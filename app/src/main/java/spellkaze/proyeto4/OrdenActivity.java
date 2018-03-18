package spellkaze.proyeto4;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class OrdenActivity extends AppCompatActivity {


    DataHolder dataHolder;
    Toposort toposort;
    ListView listView;
    Button buttonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden);
        dataHolder = DataHolder.getInstance();
        toposort = new Toposort();

        listView = (ListView)findViewById(R.id.courseToTakeList);
        buttonReturn = (Button) findViewById(R.id.button_return);
        buttonReturn.setOnClickListener(ButtonReturn);
        Log.d("DataHolder", dataHolder.getCourseList().toString());
    }

    private final View.OnClickListener ButtonReturn= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           finish();

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        toposort.setCoursesToSort(dataHolder.getCourseList());
        toposort.TopoSorting();
        UpdateUI();
    }

    private void UpdateUI()
    {
        AdapterOrderList adapterOrderList = new AdapterOrderList(this,toposort.getResult());
        listView.setAdapter(adapterOrderList);
    }
}
