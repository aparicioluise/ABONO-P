package expanse.publico.abono;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button Btn;
    TextView Tx1, text2;
    EditText caet, mget, ket, net, pet, feet, mnet, cuet, znet, set, bet;
    String datos;
    String[] resultado;
    ArrayAdapter aa;
    ProgressBar Bar;
    private int prog=0;
    Handler handeler;

    TableLayout tl_b;
    RadioGroup rg_a;
    RadioButton rb_a_1, rb_a_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn= (Button)findViewById(R.id.button);
        Tx1=(TextView)findViewById(R.id.tx1);
        text2=(TextView)findViewById(R.id.text2);

        caet=(EditText)findViewById(R.id.caet);
        mget=(EditText)findViewById(R.id.mget);
        ket=(EditText)findViewById(R.id.ket);
        net=(EditText)findViewById(R.id.net);
        pet=(EditText)findViewById(R.id.pet);
        feet=(EditText)findViewById(R.id.feet);
        mnet=(EditText)findViewById(R.id.mnet);
        cuet=(EditText)findViewById(R.id.cuet);
        znet=(EditText)findViewById(R.id.znet);
        set=(EditText)findViewById(R.id.set);
        bet=(EditText)findViewById(R.id.bet);

        Bar=(ProgressBar)findViewById(R.id.Bar);
        handeler=new Handler();

        tl_b=(TableLayout)findViewById(R.id.tl_b);
        rg_a=(RadioGroup)findViewById(R.id.rg_a);
        rb_a_1=(RadioButton)findViewById(R.id.rb_a_1);
        rb_a_2=(RadioButton)findViewById(R.id.rb_a_2);

        if (!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        tl_b.setVisibility(View.GONE);
        rg_a.clearCheck();

        rg_a.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group , int checkedId) {

                if(checkedId==R.id.rb_a_1){
                    tl_b.setVisibility(View.VISIBLE);
                }
                if(checkedId==R.id.rb_a_2){
                    tl_b.setVisibility(View.GONE);
                }
            }
        });

        barra barra=new barra();
        barra.execute();


        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Tx1.setText(" ");
                text2.setText(" ");

                // llenar todos los campos

                if (caet.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Ingrese el valor de Ca",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mget.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Ingrese el valor de Mg",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (ket.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Ingrese el valor de K",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (net.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Ingrese el valor de N",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pet.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Ingrese el valor de P",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (feet.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Ingrese el valor de Fe",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mnet.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Ingrese el valor de Mn",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (cuet.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Ingrese el valor de Cu",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (znet.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Ingrese el valor de Zn",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (set.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Ingrese el valor de S",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (bet.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Ingrese el valor de B",Toast.LENGTH_SHORT).show();
                    return;
                }

                Btn.setBackgroundColor(getResources().getColor(R.color.rojo));
                Btn.setEnabled(false);
                Bar.setVisibility(View.VISIBLE);
                prog=Bar.getProgress();

                time time=new time();
                time.execute();
            }
        });


    }

    public class time extends AsyncTask<Void,Integer,Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {


            // Obtener valor ingresado

            final String cada=String.valueOf(caet.getText());
            final String mgda=String.valueOf(mget.getText());
            final String kda=String.valueOf(ket.getText());
            final String nda=String.valueOf(net.getText());
            final String pda=String.valueOf(pet.getText());
            final String feda=String.valueOf(feet.getText());
            final String mnda=String.valueOf(mnet.getText());
            final String cuda=String.valueOf(cuet.getText());
            final String znda=String.valueOf(znet.getText());
            final String sda=String.valueOf(set.getText());
            final String bda=String.valueOf(bet.getText());

            // integracion python

            Python py= Python.getInstance();
            final PyObject pyobj = py.getModule("pyapp");

            // Envio de data



            //                PyObject nv00 = pyobj.callAttr("nv00");
            //                PyObject nv01 = pyobj.callAttr("nv01");
            //                PyObject nv02 = pyobj.callAttr("nv02");
            //                PyObject nv03 = pyobj.callAttr("nv03");
            //                PyObject nv04 = pyobj.callAttr("nv04");
            //                PyObject nv05 = pyobj.callAttr("nv05");
            //                PyObject nv06 = pyobj.callAttr("nv06");
            //                PyObject nv07 = pyobj.callAttr("nv07");
            //                PyObject nv08 = pyobj.callAttr("nv08");
            //                PyObject nv09 = pyobj.callAttr("nv09");
            //                PyObject nv10 = pyobj.callAttr("nv10");
            //                PyObject nv11 = pyobj.callAttr("nv11");


            //nv00.toString()+"\n"+nv01.toString()+"\n"+nv02.toString()+"\n"+nv03.toString()
            //                    +"\n"+nv04.toString()+"\n"+nv05.toString()+"\n"+nv06.toString()+"\n"+nv07.toString()
            //                    +"\n"+nv08.toString()+"\n"+nv09.toString()+"\n"+nv10.toString()+"\n"+nv11.toString()

            runOnUiThread(new Runnable() {

                @Override
                public void run() {


                    final PyObject intdata = pyobj.callAttr("intdata", cada, mgda, kda, nda, pda, feda, mnda, cuda, znda, sda, bda);
                    // Stuff that updates the UI
                    datos = intdata.toString();
                    String datos1=datos.replace("[", "").replace("]", "");

                    resultado= datos1.split(",");
                    Tx1.setText("Se recomiendan las siguientes cantidades de abono:"+"\n"+"\n"+
                            "NITRABOR= "+resultado[0]+" kg/ha"+"\n"+"REGA= "+resultado[1]+" kg/ha"+"\n"+"POLISULFATO= "+resultado[2]+" kg/ha"+"\n"
                            +"HIGH COMPLETE= "+resultado[3]+" kg/ha" +"\n"+"HIGH K= "+resultado[4]+" kg/ha"+"\n"+"GRANUMAX S= "+resultado[5]+
                            " kg/ha"+"\n"+"HYDRAN= "+resultado[6]+" kg/ha"+"\n"+"COLONO 25-5-20= "+resultado[7]+" kg/ha"+"\n"+"DAP= "+resultado[8]+" kg/ha"
                            +"\n"+"KICERITA= "+resultado[9]+" kg/ha"+"\n"+"DOLOMITA= "+resultado[10]+" kg/ha"+"\n"+"MICROMIX FORTE= "+resultado[11]+" kg/ha");

                    text2.setText(R.string.nota);
                }
            });
            //cambio();
            // "\n"+datos
            return true;
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {

            Btn.setBackgroundColor(getResources().getColor(R.color.colorAccent2));
            Btn.setEnabled(true);
            Bar.setVisibility(View.INVISIBLE);
        }
    }

    public class barra extends AsyncTask<Void,Integer,Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

                    while (prog<100)
                    {
                        prog++;
                        handeler.post(new Runnable() {
                            @Override
                            public void run() {
                                Bar.setProgress(prog);
                            }
                        });

                        try {
                            Thread.sleep(100);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

            return true;
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {

        }
    }
}
