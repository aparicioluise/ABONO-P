package expanse.publico.abono;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import expanse.publico.abono.R;

public class MainActivity extends AppCompatActivity {

    Button Btn;
    TextView Tx1;
    EditText caet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn= (Button)findViewById(R.id.button);
        Tx1=(TextView)findViewById(R.id.tx1);

        caet=(EditText)findViewById(R.id.caet);

        if (!Python.isStarted())
            Python.start(new AndroidPlatform(this));



        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (caet.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Ingrese el valor de Ca",Toast.LENGTH_SHORT).show();
                    return;
                }

                String cada=String.valueOf(caet.getText());

                Python py= Python.getInstance();

                final PyObject pyobj = py.getModule("pyapp");

                PyObject intdata = pyobj.callAttr("intdata",cada);

                PyObject nv00 = pyobj.callAttr("nv00");
                PyObject nv01 = pyobj.callAttr("nv01");
                PyObject nv02 = pyobj.callAttr("nv02");
                PyObject nv03 = pyobj.callAttr("nv03");
                PyObject nv04 = pyobj.callAttr("nv04");
                PyObject nv05 = pyobj.callAttr("nv05");
                PyObject nv06 = pyobj.callAttr("nv06");
                PyObject nv07 = pyobj.callAttr("nv07");
                PyObject nv08 = pyobj.callAttr("nv08");
                PyObject nv09 = pyobj.callAttr("nv09");
                PyObject nv10 = pyobj.callAttr("nv10");
                PyObject nv11 = pyobj.callAttr("nv11");


                Tx1.setText(nv00.toString()+"\n"+nv01.toString()+"\n"+nv02.toString()+"\n"+nv03.toString()
                    +"\n"+nv04.toString()+"\n"+nv05.toString()+"\n"+nv06.toString()+"\n"+nv07.toString()
                    +"\n"+nv08.toString()+"\n"+nv09.toString()+"\n"+nv10.toString()+"\n"+nv11.toString());

            }
        });


    }
}
