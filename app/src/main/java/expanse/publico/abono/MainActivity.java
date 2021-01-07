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
    EditText  caet, mget, ket, net, pet, feet, mnet, cuet, znet, set, bet;
    EditText a1_nom, a1_cos, a1_ca, a1_mg, a1_k, a1_n,a1_p,a1_fe, a1_mn, a1_cu, a1_zn,a1_s, a1_b;
    EditText a2_nom, a2_cos, a2_ca, a2_mg, a2_k, a2_n,a2_p,a2_fe, a2_mn, a2_cu, a2_zn,a2_s, a2_b;
    EditText a3_nom, a3_cos, a3_ca, a3_mg, a3_k, a3_n,a3_p,a3_fe, a3_mn, a3_cu, a3_zn,a3_s, a3_b;

    EditText cosca, densi, espes, produ, arbha;
    EditText c_rega, c_polisulfato, c_high_complete, c_high_k, c_granumax_s, c_hydran, c_colono_15_5_20,
            c_dap, c_kicerita, c_dolomita,c_micromix_forte, c_enmienda1, c_kcl, c_kmag, c_urea_x, c_boro,
            c_Magnesamon, c_nitrabor;
    EditText e1_ca, e1_mg, e1_k, e1_n, e1_p, e1_fe, e1_mn, e1_cu, e1_zn, e1_s, e1_b;
    EditText e2_ca, e2_mg, e2_k, e2_n, e2_p, e2_fe, e2_mn, e2_cu, e2_zn, e2_s, e2_b;
    EditText e3_ca, e3_mg, e3_k, e3_n, e3_p, e3_fe, e3_mn, e3_cu, e3_zn, e3_s, e3_b;
    EditText e4_ca, e4_mg, e4_k, e4_n, e4_p, e4_fe, e4_mn, e4_cu, e4_zn, e4_s, e4_b;

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

        Bar=(ProgressBar)findViewById(R.id.Bar);
        handeler=new Handler();

        tl_b=(TableLayout)findViewById(R.id.tl_b);
        rg_a=(RadioGroup)findViewById(R.id.rg_a);
        rb_a_1=(RadioButton)findViewById(R.id.rb_a_1);
        rb_a_2=(RadioButton)findViewById(R.id.rb_a_2);

        caet = (EditText) findViewById(R.id.caet);
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

        cosca=(EditText)findViewById(R.id.cosca);
        densi=(EditText)findViewById(R.id.densi);
        espes=(EditText)findViewById(R.id.espes);
        produ=(EditText)findViewById(R.id.produ);
        arbha=(EditText)findViewById(R.id.arbha);

        c_rega=(EditText)findViewById(R.id.c_rega);
        c_polisulfato=(EditText)findViewById(R.id.c_polisulfato);
        c_high_complete=(EditText)findViewById(R.id.c_high_complete);
        c_nitrabor=(EditText)findViewById(R.id.c_nitrabor);
        c_high_k=(EditText)findViewById(R.id.c_high_k);
        c_granumax_s=(EditText)findViewById(R.id.c_granumax_s);
        c_hydran=(EditText)findViewById(R.id.c_hydran);
        c_colono_15_5_20=(EditText)findViewById(R.id.c_colono_15_5_20);
        c_dap=(EditText)findViewById(R.id.c_dap);
        c_kicerita=(EditText)findViewById(R.id.c_kicerita);
        c_dolomita=(EditText)findViewById(R.id.c_dolomita);
        c_micromix_forte=(EditText)findViewById(R.id.c_micromix_forte);
        c_enmienda1=(EditText)findViewById(R.id.c_enmienda1);
        c_kcl=(EditText)findViewById(R.id.c_kcl);
        c_kmag=(EditText)findViewById(R.id.c_kmag);
        c_urea_x=(EditText)findViewById(R.id.c_urea_x);
        c_boro=(EditText)findViewById(R.id.c_boro);
        c_Magnesamon=(EditText)findViewById(R.id.c_magnesamon);

        e1_ca=(EditText)findViewById(R.id.e1_ca);
        e1_mg=(EditText)findViewById(R.id.e1_mg);
        e1_k=(EditText)findViewById(R.id.e1_k);
        e1_n=(EditText)findViewById(R.id.e1_n);
        e1_p=(EditText)findViewById(R.id.e1_p);
        e1_fe=(EditText)findViewById(R.id.e1_fe);
        e1_mn=(EditText)findViewById(R.id.e1_mn);
        e1_cu=(EditText)findViewById(R.id.e1_cu);
        e1_zn=(EditText)findViewById(R.id.e1_zn);
        e1_s=(EditText)findViewById(R.id.e1_s);
        e1_b=(EditText)findViewById(R.id.e1_b);

        e2_ca=(EditText)findViewById(R.id.e2_ca);
        e2_mg=(EditText)findViewById(R.id.e2_mg);
        e2_k=(EditText)findViewById(R.id.e2_k);
        e2_n=(EditText)findViewById(R.id.e2_n);
        e2_p=(EditText)findViewById(R.id.e2_p);
        e2_fe=(EditText)findViewById(R.id.e2_fe);
        e2_mn=(EditText)findViewById(R.id.e2_mn);
        e2_cu=(EditText)findViewById(R.id.e2_cu);
        e2_zn=(EditText)findViewById(R.id.e2_zn);
        e2_s=(EditText)findViewById(R.id.e2_s);
        e2_b=(EditText)findViewById(R.id.e2_b);

        e3_ca=(EditText)findViewById(R.id.e3_ca);
        e3_mg=(EditText)findViewById(R.id.e3_mg);
        e3_k=(EditText)findViewById(R.id.e3_k);
        e3_n=(EditText)findViewById(R.id.e3_n);
        e3_p=(EditText)findViewById(R.id.e3_p);
        e3_fe=(EditText)findViewById(R.id.e3_fe);
        e3_mn=(EditText)findViewById(R.id.e3_mn);
        e3_cu=(EditText)findViewById(R.id.e3_cu);
        e3_zn=(EditText)findViewById(R.id.e3_zn);
        e3_s=(EditText)findViewById(R.id.e3_s);
        e3_b=(EditText)findViewById(R.id.e3_b);

        e4_ca=(EditText)findViewById(R.id.e4_ca);
        e4_mg=(EditText)findViewById(R.id.e4_mg);
        e4_k=(EditText)findViewById(R.id.e4_k);
        e4_n=(EditText)findViewById(R.id.e4_n);
        e4_p=(EditText)findViewById(R.id.e4_p);
        e4_fe=(EditText)findViewById(R.id.e4_fe);
        e4_mn=(EditText)findViewById(R.id.e4_mn);
        e4_cu=(EditText)findViewById(R.id.e4_cu);
        e4_zn=(EditText)findViewById(R.id.e4_zn);
        e4_s=(EditText)findViewById(R.id.e4_s);
        e4_b=(EditText)findViewById(R.id.e4_b);

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

            final String cosca_v=String.valueOf(cosca);
            final String densi_v=String.valueOf(densi);
            final String espes_v=String.valueOf(espes);
            final String produ_v=String.valueOf(produ);
            final String arbha_v=String.valueOf(arbha);

            final String rega_v=String.valueOf(c_rega);
            final String polisulfato_v=String.valueOf(c_polisulfato);
            final String high_complete_v=String.valueOf(c_high_complete);
            final String nitrabor_v=String.valueOf(c_nitrabor);
            final String high_k_v=String.valueOf(c_high_k);
            final String granumax_s_v=String.valueOf(c_granumax_s);
            final String hydran_v=String.valueOf(c_hydran);
            final String colono_v=String.valueOf(c_colono_15_5_20);
            final String dap_v=String.valueOf(c_dap);
            final String kicerita_v=String.valueOf(c_kicerita);
            final String dolomita_v=String.valueOf(c_dolomita);
            final String micromix_forte_v=String.valueOf(c_micromix_forte);
            final String enmienda1_v=String.valueOf(c_enmienda1);
            final String kcl_v=String.valueOf(c_kcl);
            final String kmag_v=String.valueOf(c_kmag);
            final String urea_v=String.valueOf(c_urea_x);
            final String boro_v=String.valueOf(c_boro);
            final String magnesamon_v=String.valueOf(c_Magnesamon);

            final String e1_ca_v=String.valueOf(e1_ca);
            final String e1_mg_v=String.valueOf(e1_mg);
            final String e1_k_v=String.valueOf(e1_k);
            final String e1_n_v=String.valueOf(e1_n);
            final String e1_p_v=String.valueOf(e1_p);
            final String e1_fe_v=String.valueOf(e1_fe);
            final String e1_mn_v=String.valueOf(e1_mn);
            final String e1_zn_v=String.valueOf(e1_zn);
            final String e1_cu_v=String.valueOf(e1_cu);
            final String e1_s_v=String.valueOf(e1_s);
            final String e1_b_v=String.valueOf(e1_b);

            final String e2_ca_v=String.valueOf(e2_ca);
            final String e2_mg_v=String.valueOf(e2_mg);
            final String e2_k_v=String.valueOf(e2_k);
            final String e2_n_v=String.valueOf(e2_n);
            final String e2_p_v=String.valueOf(e2_p);
            final String e2_fe_v=String.valueOf(e2_fe);
            final String e2_mn_v=String.valueOf(e2_mn);
            final String e2_zn_v=String.valueOf(e2_zn);
            final String e2_cu_v=String.valueOf(e2_cu);
            final String e2_s_v=String.valueOf(e2_s);
            final String e2_b_v=String.valueOf(e2_b);

            final String e3_ca_v=String.valueOf(e3_ca);
            final String e3_mg_v=String.valueOf(e3_mg);
            final String e3_k_v=String.valueOf(e3_k);
            final String e3_n_v=String.valueOf(e3_n);
            final String e3_p_v=String.valueOf(e3_p);
            final String e3_fe_v=String.valueOf(e3_fe);
            final String e3_mn_v=String.valueOf(e3_mn);
            final String e3_zn_v=String.valueOf(e3_zn);
            final String e3_cu_v=String.valueOf(e3_cu);
            final String e3_s_v=String.valueOf(e3_s);
            final String e3_b_v=String.valueOf(e3_b);

            final String e4_ca_v=String.valueOf(e4_ca);
            final String e4_mg_v=String.valueOf(e4_mg);
            final String e4_k_v=String.valueOf(e4_k);
            final String e4_n_v=String.valueOf(e4_n);
            final String e4_p_v=String.valueOf(e4_p);
            final String e4_fe_v=String.valueOf(e4_fe);
            final String e4_mn_v=String.valueOf(e4_mn);
            final String e4_zn_v=String.valueOf(e4_zn);
            final String e4_cu_v=String.valueOf(e4_cu);
            final String e4_s_v=String.valueOf(e4_s);
            final String e4_b_v=String.valueOf(e4_b);

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
