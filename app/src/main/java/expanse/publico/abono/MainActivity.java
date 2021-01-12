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

            final String a1_nom_v=String.valueOf(a1_nom.getText());
            final String a1_cos_v=String.valueOf(a1_cos.getText());
            final String a1_ca_v=String.valueOf(a1_ca.getText());
            final String a1_mg_v=String.valueOf(a1_mg.getText());
            final String a1_k_v=String.valueOf(a1_k.getText());
            final String a1_n_v=String.valueOf(a1_n.getText());
            final String a1_p_v=String.valueOf(a1_p.getText());
            final String a1_fe_v=String.valueOf(a1_fe.getText());
            final String a1_mn_v=String.valueOf(a1_mn.getText());
            final String a1_cu_v=String.valueOf(a1_cu.getText());
            final String a1_zn_v=String.valueOf(a1_zn.getText());
            final String a1_s_v=String.valueOf(a1_s.getText());
            final String a1_b_v=String.valueOf(a1_b.getText());

            final String a2_nom_v=String.valueOf(a2_nom.getText());
            final String a2_cos_v=String.valueOf(a2_cos.getText());
            final String a2_ca_v=String.valueOf(a2_ca.getText());
            final String a2_mg_v=String.valueOf(a2_mg.getText());
            final String a2_k_v=String.valueOf(a2_k.getText());
            final String a2_n_v=String.valueOf(a2_n.getText());
            final String a2_p_v=String.valueOf(a2_p.getText());
            final String a2_fe_v=String.valueOf(a2_fe.getText());
            final String a2_mn_v=String.valueOf(a2_mn.getText());
            final String a2_cu_v=String.valueOf(a2_cu.getText());
            final String a2_zn_v=String.valueOf(a2_zn.getText());
            final String a2_s_v=String.valueOf(a2_s.getText());
            final String a2_b_v=String.valueOf(a2_b.getText());

            final String a3_nom_v=String.valueOf(a3_nom.getText());
            final String a3_cos_v=String.valueOf(a3_cos.getText());
            final String a3_ca_v=String.valueOf(a3_ca.getText());
            final String a3_mg_v=String.valueOf(a3_mg.getText());
            final String a3_k_v=String.valueOf(a3_k.getText());
            final String a3_n_v=String.valueOf(a3_n.getText());
            final String a3_p_v=String.valueOf(a3_p.getText());
            final String a3_fe_v=String.valueOf(a3_fe.getText());
            final String a3_mn_v=String.valueOf(a3_mn.getText());
            final String a3_cu_v=String.valueOf(a3_cu.getText());
            final String a3_zn_v=String.valueOf(a3_zn.getText());
            final String a3_s_v=String.valueOf(a3_s.getText());
            final String a3_b_v=String.valueOf(a3_b.getText());

            final String cosca_v=String.valueOf(cosca.getText());
            final String densi_v=String.valueOf(densi.getText());
            final String espes_v=String.valueOf(espes.getText());
            final String produ_v=String.valueOf(produ.getText());
            final String arbha_v=String.valueOf(arbha.getText());

            final String rega_v=String.valueOf(c_rega.getText());
            final String polisulfato_v=String.valueOf(c_polisulfato.getText());
            final String high_complete_v=String.valueOf(c_high_complete.getText());
            final String nitrabor_v=String.valueOf(c_nitrabor.getText());
            final String high_k_v=String.valueOf(c_high_k.getText());
            final String granumax_s_v=String.valueOf(c_granumax_s.getText());
            final String hydran_v=String.valueOf(c_hydran.getText());
            final String colono_v=String.valueOf(c_colono_15_5_20.getText());
            final String dap_v=String.valueOf(c_dap.getText());
            final String kicerita_v=String.valueOf(c_kicerita.getText());
            final String dolomita_v=String.valueOf(c_dolomita.getText());
            final String micromix_forte_v=String.valueOf(c_micromix_forte.getText());
            final String enmienda1_v=String.valueOf(c_enmienda1.getText());
            final String kcl_v=String.valueOf(c_kcl.getText());
            final String kmag_v=String.valueOf(c_kmag.getText());
            final String urea_v=String.valueOf(c_urea_x.getText());
            final String boro_v=String.valueOf(c_boro.getText());
            final String magnesamon_v=String.valueOf(c_Magnesamon.getText());

            final String e1_ca_v=String.valueOf(e1_ca.getText());
            final String e1_mg_v=String.valueOf(e1_mg.getText());
            final String e1_k_v=String.valueOf(e1_k.getText());
            final String e1_n_v=String.valueOf(e1_n.getText());
            final String e1_p_v=String.valueOf(e1_p.getText());
            final String e1_fe_v=String.valueOf(e1_fe.getText());
            final String e1_mn_v=String.valueOf(e1_mn.getText());
            final String e1_zn_v=String.valueOf(e1_zn.getText());
            final String e1_cu_v=String.valueOf(e1_cu.getText());
            final String e1_s_v=String.valueOf(e1_s.getText());
            final String e1_b_v=String.valueOf(e1_b.getText());

            final String e2_ca_v=String.valueOf(e2_ca.getText());
            final String e2_mg_v=String.valueOf(e2_mg.getText());
            final String e2_k_v=String.valueOf(e2_k.getText());
            final String e2_n_v=String.valueOf(e2_n.getText());
            final String e2_p_v=String.valueOf(e2_p.getText());
            final String e2_fe_v=String.valueOf(e2_fe.getText());
            final String e2_mn_v=String.valueOf(e2_mn.getText());
            final String e2_zn_v=String.valueOf(e2_zn.getText());
            final String e2_cu_v=String.valueOf(e2_cu.getText());
            final String e2_s_v=String.valueOf(e2_s.getText());
            final String e2_b_v=String.valueOf(e2_b.getText());

            final String e3_ca_v=String.valueOf(e3_ca.getText());
            final String e3_mg_v=String.valueOf(e3_mg.getText());
            final String e3_k_v=String.valueOf(e3_k.getText());
            final String e3_n_v=String.valueOf(e3_n.getText());
            final String e3_p_v=String.valueOf(e3_p.getText());
            final String e3_fe_v=String.valueOf(e3_fe.getText());
            final String e3_mn_v=String.valueOf(e3_mn.getText());
            final String e3_zn_v=String.valueOf(e3_zn.getText());
            final String e3_cu_v=String.valueOf(e3_cu.getText());
            final String e3_s_v=String.valueOf(e3_s.getText());
            final String e3_b_v=String.valueOf(e3_b.getText());

            final String e4_ca_v=String.valueOf(e4_ca.getText());
            final String e4_mg_v=String.valueOf(e4_mg.getText());
            final String e4_k_v=String.valueOf(e4_k.getText());
            final String e4_n_v=String.valueOf(e4_n.getText());
            final String e4_p_v=String.valueOf(e4_p.getText());
            final String e4_fe_v=String.valueOf(e4_fe.getText());
            final String e4_mn_v=String.valueOf(e4_mn.getText());
            final String e4_zn_v=String.valueOf(e4_zn.getText());
            final String e4_cu_v=String.valueOf(e4_cu.getText());
            final String e4_s_v=String.valueOf(e4_s.getText());
            final String e4_b_v=String.valueOf(e4_b.getText());

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

                    final PyObject intdata = pyobj.callAttr("intdata", cada, mgda, kda, nda, pda, feda, mnda, cuda, znda, sda, bda,
                            10, a1_cos_v, a1_ca_v, a1_mg_v, a1_k_v, a1_n_v, a1_p_v, a1_fe_v, a1_mn_v, a1_cu_v, a1_zn_v, a1_s_v,a1_b_v,
                            10, a2_cos_v, a2_ca_v, a2_mg_v, a2_k_v, a2_n_v, a2_p_v, a2_fe_v, a2_mn_v, a2_cu_v, a2_zn_v, a2_s_v,a2_b_v,
                            10, a3_cos_v, a3_ca_v, a3_mg_v, a3_k_v, a3_n_v, a3_p_v, a3_fe_v, a3_mn_v, a3_cu_v, a3_zn_v, a3_s_v,a3_b_v,
                            cosca_v, densi_v, espes_v, produ_v, arbha_v, rega_v, polisulfato_v,high_complete_v, nitrabor_v, high_k_v,granumax_s_v,
                            hydran_v, colono_v, dap_v, kicerita_v, dolomita_v, micromix_forte_v, enmienda1_v, kcl_v, kmag_v, urea_v,boro_v, magnesamon_v,
                            e1_ca_v, e1_mg_v, e1_k_v, e1_n_v, e1_p_v, e1_fe_v, e1_mn_v, e1_zn_v, e1_cu_v, e1_s_v, e1_b_v,
                            e2_ca_v, e2_mg_v, e2_k_v, e2_n_v, e2_p_v, e2_fe_v, e2_mn_v, e2_zn_v, e2_cu_v, e2_s_v, e2_b_v,
                            e3_ca_v, e3_mg_v, e3_k_v, e3_n_v, e3_p_v, e3_fe_v, e3_mn_v, e3_zn_v, e3_cu_v, e3_s_v, e3_b_v,
                            e4_ca_v, e4_mg_v, e4_k_v, e4_n_v, e4_p_v, e4_fe_v, e4_mn_v, e4_zn_v, e4_cu_v, e4_s_v, e4_b_v);
                    // Stuff that updates the UI

                    datos = intdata.toString();
                    String datos1=datos.replace("[", "").replace("]", "").replace("'","").replace("(","").replace(")","");

                    resultado= datos1.split(",");

                    float cosfin=Float.parseFloat(resultado[172])+Float.parseFloat(resultado[173])+Float.parseFloat(resultado[174])+Float.parseFloat(resultado[175]);

                    Tx1.setText("RESULTADOS"+"\n"+"\n"+"Con un costo en abono anual estimado de B/."+cosfin+" se recomiendan las siguientes cantidades de abono en 4 aplicaciones:"+"\n"+"\n"+
                            "APLICACIÓN 1"+"\n"+"\n"+"Se estima un costo en abono de B/."+resultado[172]+" y un costo en neutralizar de B/."+resultado[176]+"\n"
                            +"Para lograr el aporte necesario aplicar"+resultado[168]+" kg/ha de abono, de una mezcla con las siguientes cantidades:"+"\n"+
                            resultado[84]+"= "+resultado[0]+" kg/ha"+"\n"+ resultado[85]+"= "+resultado[1]+" kg/ha"+"\n"+resultado[86]+"= "+resultado[2]+" kg/ha"+"\n"
                            +resultado[87]+"= "+resultado[3]+" kg/ha" +"\n"+resultado[88]+"= "+resultado[4]+" kg/ha"+"\n"+resultado[89]+"= "+resultado[5]+" kg/ha"+"\n"
                            +resultado[90]+"= "+resultado[6]+" kg/ha"+"\n"+resultado[91]+"= "+resultado[7]+" kg/ha"+"\n"+resultado[92]+"= "+resultado[8]+" kg/ha" +"\n"
                            +resultado[93]+"= "+resultado[9]+" kg/ha"+"\n"+resultado[94]+"= "+resultado[10]+" kg/ha"+"\n"+resultado[95]+"= "+resultado[11]+" kg/ha"+"\n"
                            +resultado[96]+"= "+resultado[12]+" kg/ha"+"\n"+resultado[97]+"= "+resultado[13]+" kg/ha"+"\n"+resultado[98]+"= "+resultado[14]+" kg/ha"+"\n"
                            +resultado[99]+"= "+resultado[15]+" kg/ha"+"\n"+resultado[100]+"= "+resultado[16]+" kg/ha"+"\n"+resultado[101]+"= "+resultado[17]+" kg/ha"+"\n"
                            +a1_nom_v+"= "+resultado[18]+" kg/ha"+"\n"+a2_nom_v+"= "+resultado[19]+" kg/ha"+"\n"+a3_nom_v+"= "+resultado[20]+" kg/ha"+"\n"+"\n"
                            +"APLICACIÓN 2"+"\n"+"\n"+"Se estima un costo en abono de B/."+resultado[173]+" y un costo en neutralizar de B/."+resultado[177]+"\n"
                            +"Para lograr el aporte necesario aplicar"+resultado[169]+" kg/ha de abono, de una mezcla con las siguientes cantidades:"+"\n"
                            +resultado[105]+"= "+resultado[21]+" kg/ha"+"\n"+resultado[106]+"= "+resultado[22]+" kg/ha"+"\n"+resultado[107]+"= "+resultado[23]+" kg/ha"+"\n"
                            +resultado[108]+"= "+resultado[24]+" kg/ha"+"\n"+resultado[109]+"= "+resultado[25]+" kg/ha"+"\n"+resultado[110]+"= "+resultado[26]+" kg/ha"+"\n"
                            +resultado[111]+"= "+resultado[27]+" kg/ha"+"\n"+resultado[112]+"= "+resultado[28]+" kg/ha"+"\n"+resultado[113]+"= "+resultado[29]+" kg/ha"+"\n"
                            +resultado[114]+"= "+resultado[30]+" kg/ha"+"\n"+resultado[115]+"= "+resultado[31]+" kg/ha"+"\n"+resultado[116]+"= "+resultado[32]+" kg/ha"+"\n"
                            +resultado[117]+"= "+resultado[33]+" kg/ha"+"\n"+resultado[118]+"= "+resultado[34]+" kg/ha"+"\n"+resultado[119]+"= "+resultado[35]+" kg/ha"+"\n"
                            +resultado[120]+"= "+resultado[36]+" kg/ha"+"\n"+resultado[121]+"= "+resultado[37]+" kg/ha"+"\n"+resultado[122]+"= "+resultado[38]+" kg/ha"+"\n"
                            +a1_nom_v+"= "+resultado[39]+" kg/ha"+"\n"+a2_nom_v+"= "+resultado[40]+" kg/ha"+"\n"+a3_nom_v+"= "+resultado[41]+" kg/ha"+"\n"+"\n"
                            +"APLICACIÓN 3"+"\n"+"\n"+"Se estima un costo en abono de B/."+resultado[174]+" y un costo en neutralizar de B/."+resultado[178]+"\n"
                            +"Para lograr el aporte necesario aplicar"+resultado[170]+" kg/ha de abono, de una mezcla con las siguientes cantidades:"+"\n"
                            +resultado[126]+"= "+resultado[42]+" kg/ha"+"\n"+resultado[127]+"= "+resultado[43]+" kg/ha"+"\n"+resultado[128]+"= "+resultado[44]+" kg/ha"+"\n"
                            +resultado[129]+"= "+resultado[45]+" kg/ha"+"\n"+resultado[130]+"= "+resultado[46]+" kg/ha"+"\n"+resultado[131]+"= "+resultado[47]+" kg/ha"+"\n"
                            +resultado[132]+"= "+resultado[48]+" kg/ha"+"\n"+resultado[133]+"= "+resultado[49]+" kg/ha"+"\n"+resultado[134]+"= "+resultado[50]+" kg/ha"+"\n"
                            +resultado[135]+"= "+resultado[51]+" kg/ha"+"\n"+resultado[136]+"= "+resultado[52]+" kg/ha"+"\n"+resultado[137]+"= "+resultado[53]+" kg/ha"+"\n"
                            +resultado[138]+"= "+resultado[54]+" kg/ha"+"\n"+resultado[139]+"= "+resultado[55]+" kg/ha"+"\n"+resultado[140]+"= "+resultado[56]+" kg/ha"+"\n"
                            +resultado[141]+"= "+resultado[57]+" kg/ha"+"\n"+resultado[142]+"= "+resultado[58]+" kg/ha"+"\n"+resultado[143]+"= "+resultado[59]+" kg/ha"+"\n"
                            +a1_nom_v+"= "+resultado[60]+" kg/ha"+"\n"+a2_nom_v+"= "+resultado[61]+" kg/ha"+"\n"+a3_nom_v+"= "+resultado[62]+" kg/ha"+"\n"+"\n"
                            +"APLICACIÓN 4"+"\n"+"\n"+"Se estima un costo en abono de B/."+resultado[175]+" y un costo en neutralizar de B/."+resultado[179]+"\n"
                            +"Para lograr el aporte necesario aplicar"+resultado[171]+" kg/ha de abono, de una mezcla con las siguientes cantidades:"+"\n"
                            +resultado[147]+"= "+resultado[63]+" kg/ha"+"\n"+resultado[148]+"= "+resultado[64]+" kg/ha"+"\n"+resultado[149]+"= "+resultado[65]+" kg/ha"+"\n"
                            +resultado[150]+"= "+resultado[66]+" kg/ha"+"\n"+resultado[151]+"= "+resultado[67]+" kg/ha"+"\n"+resultado[152]+"= "+resultado[68]+" kg/ha"+"\n"
                            +resultado[153]+"= "+resultado[69]+" kg/ha"+"\n"+resultado[154]+"= "+resultado[70]+" kg/ha"+"\n"+resultado[155]+"= "+resultado[71]+" kg/ha"+"\n"
                            +resultado[156]+"= "+resultado[72]+" kg/ha"+"\n"+resultado[157]+"= "+resultado[73]+" kg/ha"+"\n"+resultado[158]+"= "+resultado[74]+" kg/ha"+"\n"
                            +resultado[159]+"= "+resultado[75]+" kg/ha"+"\n"+resultado[160]+"= "+resultado[76]+" kg/ha"+"\n"+resultado[161]+"= "+resultado[77]+" kg/ha"+"\n"
                            +resultado[162]+"= "+resultado[78]+" kg/ha"+"\n"+resultado[163]+"= "+resultado[79]+" kg/ha"+"\n"+resultado[164]+"= "+resultado[80]+" kg/ha"+"\n"
                            +a1_nom_v+"= "+resultado[81]+" kg/ha"+"\n"+a2_nom_v+"= "+resultado[82]+" kg/ha"+"\n"+a3_nom_v+"= "+resultado[83]+" kg/ha"+"\n"
                    );

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
