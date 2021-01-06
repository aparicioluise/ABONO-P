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
    EditText a1_nom, a1_cos, a1_ca, a1_mg, a1_k, a1_n,a1_p,a1_fe, a1_mn, a1_cu, a1_zn,a1_s, a1_b;
    EditText a2_nom, a2_cos, a2_ca, a2_mg, a2_k, a2_n,a2_p,a2_fe, a2_mn, a2_cu, a2_zn,a2_s, a2_b;
    EditText a3_nom, a3_cos, a3_ca, a3_mg, a3_k, a3_n,a3_p,a3_fe, a3_mn, a3_cu, a3_zn,a3_s, a3_b;

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

        a1_nom=(EditText)findViewById(R.id.a1_nom);
        a1_cos=(EditText)findViewById(R.id.a1_cos);
        a1_ca=(EditText)findViewById(R.id.a1_ca);
        a1_mg=(EditText)findViewById(R.id.a1_mg);
        a1_k=(EditText)findViewById(R.id.a1_k);
        a1_n=(EditText)findViewById(R.id.a1_n);
        a1_p=(EditText)findViewById(R.id.a1_p);
        a1_fe=(EditText)findViewById(R.id.a1_fe);
        a1_mn=(EditText)findViewById(R.id.a1_mn);
        a1_cu=(EditText)findViewById(R.id.a1_cu);
        a1_zn=(EditText)findViewById(R.id.a1_zn);
        a1_s=(EditText)findViewById(R.id.a1_s);
        a1_b=(EditText)findViewById(R.id.a1_b);

        a2_nom=(EditText)findViewById(R.id.a2_nom);
        a2_cos=(EditText)findViewById(R.id.a2_cos);
        a2_ca=(EditText)findViewById(R.id.a2_ca);
        a2_mg=(EditText)findViewById(R.id.a2_mg);
        a2_k=(EditText)findViewById(R.id.a2_k);
        a2_n=(EditText)findViewById(R.id.a2_n);
        a2_p=(EditText)findViewById(R.id.a2_p);
        a2_fe=(EditText)findViewById(R.id.a2_fe);
        a2_mn=(EditText)findViewById(R.id.a2_mn);
        a2_cu=(EditText)findViewById(R.id.a2_cu);
        a2_zn=(EditText)findViewById(R.id.a2_zn);
        a2_s=(EditText)findViewById(R.id.a2_s);
        a2_b=(EditText)findViewById(R.id.a2_b);

        a3_nom=(EditText)findViewById(R.id.a3_nom);
        a3_cos=(EditText)findViewById(R.id.a3_cos);
        a3_ca=(EditText)findViewById(R.id.a3_ca);
        a3_mg=(EditText)findViewById(R.id.a3_mg);
        a3_k=(EditText)findViewById(R.id.a3_k);
        a3_n=(EditText)findViewById(R.id.a3_n);
        a3_p=(EditText)findViewById(R.id.a3_p);
        a3_fe=(EditText)findViewById(R.id.a3_fe);
        a3_mn=(EditText)findViewById(R.id.a3_mn);
        a3_cu=(EditText)findViewById(R.id.a3_cu);
        a3_zn=(EditText)findViewById(R.id.a3_zn);
        a3_s=(EditText)findViewById(R.id.a3_s);
        a3_b=(EditText)findViewById(R.id.a3_b);

        Bar=(ProgressBar)findViewById(R.id.Bar);
        handeler=new Handler();

        tl_b=(TableLayout)findViewById(R.id.tl_b);
        rg_a=(RadioGroup)findViewById(R.id.rg_a);
        rb_a_1=(RadioButton)findViewById(R.id.rb_a_1);
        rb_a_2=(RadioButton)findViewById(R.id.rb_a_2);

        if (!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        tl_b.setVisibility(View.GONE);
        //rg_a.clearCheck();


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


            final String a1_nom_v=String.valueOf(a1_nom);
            final String a1_cos_v=String.valueOf(a1_cos);
            final String a1_ca_v=String.valueOf(a1_ca);
            final String a1_mg_v=String.valueOf(a1_mg);
            final String a1_k_v=String.valueOf(a1_k);
            final String a1_n_v=String.valueOf(a1_n);
            final String a1_p_v=String.valueOf(a1_p);
            final String a1_fe_v=String.valueOf(a1_fe);
            final String a1_mn_v=String.valueOf(a1_mn);
            final String a1_cu_v=String.valueOf(a1_cu);
            final String a1_zn_v=String.valueOf(a1_zn);
            final String a1_s_v=String.valueOf(a1_s);
            final String a1_b_v=String.valueOf(a1_b);

            final String a2_nom_v=String.valueOf(a2_nom);
            final String a2_cos_v=String.valueOf(a2_cos);
            final String a2_ca_v=String.valueOf(a2_ca);
            final String a2_mg_v=String.valueOf(a2_mg);
            final String a2_k_v=String.valueOf(a2_k);
            final String a2_n_v=String.valueOf(a2_n);
            final String a2_p_v=String.valueOf(a2_p);
            final String a2_fe_v=String.valueOf(a2_fe);
            final String a2_mn_v=String.valueOf(a2_mn);
            final String a2_cu_v=String.valueOf(a2_cu);
            final String a2_zn_v=String.valueOf(a2_zn);
            final String a2_s_v=String.valueOf(a2_s);
            final String a2_b_v=String.valueOf(a2_b);

            final String a3_nom_v=String.valueOf(a3_nom);
            final String a3_cos_v=String.valueOf(a3_cos);
            final String a3_ca_v=String.valueOf(a3_ca);
            final String a3_mg_v=String.valueOf(a3_mg);
            final String a3_k_v=String.valueOf(a3_k);
            final String a3_n_v=String.valueOf(a3_n);
            final String a3_p_v=String.valueOf(a3_p);
            final String a3_fe_v=String.valueOf(a3_fe);
            final String a3_mn_v=String.valueOf(a3_mn);
            final String a3_cu_v=String.valueOf(a3_cu);
            final String a3_zn_v=String.valueOf(a3_zn);
            final String a3_s_v=String.valueOf(a3_s);
            final String a3_b_v=String.valueOf(a3_b);

            // integracion python

            Python py= Python.getInstance();

            final PyObject pyobj = py.getModule("pyapp");

            if(rb_a_1.isSelected()){
                // SI
            }
            else if (rb_a_2.isSelected()){
                // NO
            }


            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    // debe ser una cadena con 50 valores

                    final PyObject intdata = pyobj.callAttr("intdata", cada, mgda, kda, nda, pda, feda, mnda, cuda, znda, sda, bda, a1_nom_v);
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
