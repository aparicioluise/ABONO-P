#codigo para determinar la combinación que produce el minimo costo 
#entre un set de posibilidades de fertilizantes, enmiendas
#considerando un set de requerimientos de elementos
#jose.gallardo@utp.ac.pa  octubre/2020


def intdata (cada,mgda,kda,nda,pda,feda,mnda,cuda,znda,sda,bda,
a1_nom_v, a1_cos_v, a1_ca_v, a1_mg_v, a1_k_v, a1_n_v, a1_p_v, a1_fe_v, a1_mn_v, a1_cu_v, a1_zn_v, a1_s_v,a1_b_v,
a2_nom_v, a2_cos_v, a2_ca_v, a2_mg_v, a2_k_v, a2_n_v, a2_p_v, a2_fe_v, a2_mn_v, a2_cu_v, a2_zn_v, a2_s_v,a2_b_v,
a3_nom_v, a3_cos_v, a3_ca_v, a3_mg_v, a3_k_v, a3_n_v, a3_p_v, a3_fe_v, a3_mn_v, a3_cu_v, a3_zn_v, a3_s_v,a3_b_v,
cosca_v, densi_v, espes_v, produ_v, arbha_v, rega_v, polisulfato_v,high_complete_v, nitrabor_v, high_k_v,granumax_s_v,
hydran_v, colono_v, dap_v, kicerita_v, dolomita_v, micromix_forte_v, enmienda1_v, kcl_v, kmag_v, urea_v,boro_v, magnesamon_v,
e1_ca_v, e1_mg_v, e1_k_v, e1_n_v, e1_p_v, e1_fe_v, e1_mn_v, e1_zn_v, e1_cu_v, e1_s_v, e1_b_v,
e2_ca_v, e2_mg_v, e2_k_v, e2_n_v, e2_p_v, e2_fe_v, e2_mn_v, e2_zn_v, e2_cu_v, e2_s_v, e2_b_v,
e3_ca_v, e3_mg_v, e3_k_v, e3_n_v, e3_p_v, e3_fe_v, e3_mn_v, e3_zn_v, e3_cu_v, e3_s_v, e3_b_v,
e4_ca_v, e4_mg_v, e4_k_v, e4_n_v, e4_p_v, e4_fe_v, e4_mn_v, e4_zn_v, e4_cu_v, e4_s_v, e4_b_v):

    lista1=[]
    lista2=[]
    lista3=[]
    lista4=[]
    lista5=[]

    #_______________

    cada= float(cada)
    mgda= float(mgda)
    kda= float(kda)
    nda= float(nda)
    pda= float(pda)
    feda= float(feda)
    mnda= float(mnda)
    cuda= float(cuda)
    znda= float(znda)
    sda= float(sda)
    bda= float(bda)

    e1_ca_v=float(e1_ca_v)
    e1_mg_v=float(e1_mg_v)

    a1_cos_v= float(a1_cos_v)

    

    #SECCION 0000000000000000000000000000000000000000000000000000000000000000000000
    # DATOS CONSTANTES
    costo_CaCO3_kg= 0.10
    #densidad y espesor de la capa de suelo de inter�s
    densidad=1200 #kg/m3
    espesor=0.2 #m
    #producción estimada
    produccion_kg_ha=1800 #40 quintales/hectarea = 1800 kg/hectarea
    #densidad (arboles por hectarea)
    arb_ha=3000

    fraccion_aprovechada=1
    fraccion_existente_disponible={'Ca':1,
    'Mg':1,
    'K':1,
    'P':1,
    'N':1, 
    'Fe':1,
    'Mn':1,
    'Cu':1,
    'Zn':1,
    'S':1, 
    'B':1}
    kg_mol={'N':0.014,'Mg':0.0243,'P':0.031,'S':0.0321,'K':0.039,
            'Ca':0.0401,'Mn':0.0549,'Fe':0.0558,'Cu':0.0635,'Zn':0.0654}
    eq_mol={'Mg':2,'K':1,'Ca':2}

    def calcular_aporte_necesario(nut_lista,contenido_sugerido,contenido_existente,
                                produccion_kg_ha,arb_ha,densidad,
                                espesor):
        construccion={}
        consumo={}
        mantenimiento =calcular_necesidad_mantenimiento(arb_ha)
        produccion = calcular_necesidad_produccion(produccion_kg_ha)
        print("------------------------------")
        for nutriente in nut_lista:    
            sugerido = kg_ha(nutriente,contenido_sugerido[nutriente],densidad,
                            espesor)
            existencia = kg_ha(nutriente,contenido_existente[nutriente],densidad,
                            espesor)
            deficiencia = max(0,sugerido-existencia)
            disponible = (max(0,existencia-sugerido)*
                        fraccion_existente_disponible[nutriente])
            construccion[nutriente] = deficiencia  
            mto=mantenimiento[nutriente]
            pro=produccion[nutriente]
            consumo[nutriente]=max(0,mto+pro-disponible)
            print('-------------------------------')
            print(nutriente)
            print_rounded(" deficiencia:",deficiencia,
                        "(sugerido:",sugerido,"existencia:",existencia,')')
            print_rounded("consumo:",consumo[nutriente],'(mantenimimento:',mto,
                        ' produccion:',pro,')', 'existencia disponible ',disponible)
        return(construccion, consumo)

    def print_rounded(*args):
        text=""
        for v in args:
            if type(v)==float or type(v) == int:
                text +=str(round(v,3))+" "
            else:
                text += v + " "
        print(text)
        
    def equiv(compuesto,peso):
        if compuesto == "P2O5":
            factor= 0.4364
        if compuesto == "K2O":
            factor = 0.8301
        if compuesto == "KCl":
            factor = 0.5244
        if compuesto == "CaO":
            factor = 0.7146
        if compuesto == "MgO":
            factor = 0.6031
        if compuesto == "SO4":
            factor = 0.333
        return peso*factor

    #función para transformar de diversas unidades a kilogramos por 
    #hectarea
    def kg_ha(elemento,contenido, densidad, espesor):
        x=contenido['contenido']
        u=contenido['unidades']
        kg_suelo_ha=espesor*10000*densidad #kg/ha
        if u == 'meq%':
            x *= 1/1000/100/eq_mol[elemento]*1000*100*kg_mol[elemento]*kg_suelo_ha/100
        if u =='cmol+/kg_suelo':
            x *= kg_mol[elemento]/eq_mol[elemento]/100*kg_suelo_ha
        if u =='mg/kg_suelo'or u=='ppm' or u=='ppm(Troug)':
            x *= kg_suelo_ha/1e6
        if u =='ppm(Olsen-Davin)':
            x *= kg_suelo_ha/1e6*22/45
        if u =='%':
            x *= kg_suelo_ha/100
        if u =='mg/L':
            x *= 1e-6*(1000)*(espesor*10000)  #1000 L / metrocubico
        return(x)

    def normalizar_composicion_de_abono(a):
        for abono, contenido in a.items():
            for nutriente in contenido.keys():
                a[abono][nutriente]/=100
        #MODIFICAR contenidos definidos en terminos de moleculas
        for abono, contenido in a.items():
            a[abono]["P"]=equiv("P2O5",a[abono]["P2O5"])  
            del a[abono]["P2O5"]    
            a[abono]["K"]=equiv("K2O",a[abono]["K2O"])
            del a[abono]["K2O"]
            a[abono]["Ca"]=equiv("CaO",a[abono]['CaO'])
            del a[abono]['CaO']
            a[abono]["Mg"]=equiv("MgO",a[abono]["MgO"])
            del a[abono]["MgO"]
            if 'SO4' in a[abono]:
                a[abono]["S"]=equiv("SO4",a[abono]["SO4"])
                del a[abono]["SO4"]
        return(a)

    #Composición de cada tipo de abono (en porcentaje)
    a={
    "Nitrabor":{"N":15.45,"P2O5":0,"K2O":0,"CaO":25.5,"MgO":0,"SO4":0,
    "Zn":0,"B":0.3,"Fe":0,"Mn":0,"Cu":0, },

    "Rega":{"N":20,"P2O5":5,"K2O":18,"CaO":0,"MgO":0,"SO4":2,
    "Zn":0.01,"B":0.04,"Fe":0,"Mn":0,"Cu":0},

    "Polisulfato":{"N":0,"P2O5":0,"K2O":14,"CaO":17,"MgO":6,"SO4":60,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0}, 

    "High_Complete":{"N":15,"P2O5":9,"K2O":20,"CaO":0,"MgO":2,"SO4":2,
    "Zn":0,"B":0.02,"Fe":0,"Mn":0,"Cu":0},

    "High_K":{"N":13,"P2O5":13,"K2O":24,"CaO":0,"MgO":0,"SO4":0,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0},

    "Granumax_S":{"N":0,"P2O5":0,"K2O":0,"CaO":15,"MgO":23,"SO4":8,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0},

    "Hydran":{"N":19,"P2O5":4,"K2O":19,"CaO":0,"MgO":2,"SO4":1.8,
    "Zn":0.1,"B":0.1,"Fe":0,"Mn":0,"Cu":0},

    "Colono_15-5-20":{"N":15,"P2O5":5,"K2O":20,"CaO":0,"MgO":2,"SO4":30,
    "Zn":0.02,"B":0.5,"Fe":0,"Mn":0,"Cu":0}, 

    "DAP":{"N":18,"P2O5":46,"K2O":0,"CaO":0,"MgO":0,"SO4":0,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0}, 

    "kicerita":{"N":0,"P2O5":0,"K2O":0,"CaO":0,"MgO":14.9,"SO4":62,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0},

    "dolomita":{"N":0,"P2O5":0,"K2O":0,"CaO":32.4,"MgO":18.2,"SO4":0,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0},

    "enmienda1":{"N":0,"P2O5":0,"K2O":0,"CaO":30,"MgO":15,"SO4":15,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0},

    "KCL":{"N":0,"P2O5":0,"K2O":60,"CaO":0,"MgO":0,"SO4":0,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0},

    "KMAG":{"N":0,"P2O5":0,"K2O":22,"CaO":0,"MgO":18,"S":22,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0},

    "Urea x":{"N":46,"P2O5":0,"K2O":0,"CaO":0,"MgO":0,"SO4":0,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0},

    "Boro gra 9.9B":{"N":0,"P2O5":0,"K2O":0,"CaO":0,"MgO":0,"SO4":0,
    "Zn":0,"B":9.9,"Fe":0,"Mn":0,"Cu":0},

    "Magnesamon":{"N":21,"P2O5":0,"K2O":0,"CaO":11,"MgO":7.5,"SO4":0,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0},

    "micromix_forte":{"N":3,"P2O5":3,"K2O":0,"CaO":2,"MgO":4,"SO4":15,
    "Zn":12,"B":1.5,"Fe":3,"Mn":1,"Cu":1},

    "ABONO1":{"N":0,"P2O5":0,"K2O":0,"CaO":0,"MgO":0,"SO4":0,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0},

    "ABONO2":{"N":0,"P2O5":0,"K2O":0,"CaO":0,"MgO":0,"SO4":0,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0},

    "ABONO3":{"N":0,"P2O5":0,"K2O":0,"CaO":0,"MgO":0,"SO4":0,
    "Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0}

    }
    a = normalizar_composicion_de_abono(a)

    costo_kg={
    "Rega":000.8,
    "Polisulfato":000.58, 
    "High_Complete":000.6,
    "Nitrabor":00.7,
    "High_K":000.6,
    "Granumax_S":000.5,
    "Hydran":000.6,
    "Colono_15-5-20":000.6, 
    "DAP":000.5,#2020 IDEAL JMGA
    "kicerita":000.36,#2020 
    "dolomita":000.36,
    "micromix_forte":0.8,
    "enmienda1":0.32,#IDEAL JMGA
    "KCL":000.42,#2020 IDEAL JMGA
    "KMAG":0.59,#2020 IDEAL JMGA
    'Urea x':0.46,#2020 IDEAL JMGA
    'Boro gra 9.9B':016.25,#IDEAL JMGA
    'Magnesamon':000.37,#IDEAL JMGA
    'ABONO1':1,
    'ABONO2':1,
    "ABONO3":1
    }
    #({Cerisola 2015 universidad nacional de la plata})
    #espinosa y molina 1999 International plant nutrition institute
    #amonicaco anhidro  -148CaCO3
    #cloruro de amonio -128CaCO3
    #urea -75-84CaCO3
    #sulfato de amonio -110-112CaCo3   540CaCO3/N*0.21 = 114
    #nitrato de amonio -60-63CaCO3     540CaCO3/N*(35/2)
    #carbonato de calcio 100
    #dolomita 108
    #Hidroxido de calcio 138
    #oxido de magnesio MgO 248
    #oxido de calcio CaO   179
    #fosfato de amonio (100% amoniacal)

    #OTROS
    #ureico(CO(NH2)2 lento 1.8CaCO3/N  
    #nitrato de amonio (33.5N) NH4NO3 0.60CaCO3/kg
    #poder de (-)neutralizaci�n, o acidificaci�n
    a_CaCO3={
    "Nitrabor":0.012*540,#1.2%amoniacal
    "Rega":0.104*540,
    "Polisulfato":0, 
    "High_Complete":0.08*540, #8 amoniacal(NH3;NH4+), 7 nitrico (NO3-) 0.0CaCO3 planta
    "High_K":0.07*540,#7amoniacal
    "Granumax_S":-105,
    "Hydran":0.10*540,#10%amoniacal
    "Colono_15-5-20":50, 
    "DAP":69, #no coincide con 0.18*540
    "kicerita":-0,
    "dolomita":-108,
    "enmienda1":-100,
    "micromix_forte":0,
    "KCL":100.,#2020
    "KMAG":0.,#2020
    'Urea x':84,#2020
    'Boro gra 9.9B':0,
    'Magnesamon':0,
    'ABONO1':0,
    'ABONO2':0,
    "ABONO3":0
    }

    #INPUT T�cnico, los valore listados son recomendaciones
    #fracci�n del abono que se considera que ser� perdido ()

    #Ca,Mg,K,P,N,OM para suelos con 28% finos (Wintgens tabla 8.1.2)
    #funciona 18 a 28% finos 
    #Fe,Mn,Cu,Zn sugerido por Fertiliza (falta sugerencia boro y azufre)
    contenido_sugerido={
    'Ca':{'contenido':5.35,'unidades':'meq%'},
    'Mg':{'contenido':0.95,'unidades':'meq%'}, 
    'K':{'contenido':0.42,'unidades':'meq%'},
    'P':{'contenido':equiv('P2O5',13),'unidades':'ppm(Troug)'},#30mg/kg bot32
    'N':{'contenido':1.74,'unidades':'mg/L'}, 
    'Fe':{'contenido':10,'unidades':'mg/kg_suelo'},
    'Mn':{'contenido':5,'unidades':'mg/kg_suelo'},
    'Cu':{'contenido':2,'unidades':'mg/kg_suelo'},
    'Zn':{'contenido':3,'unidades':'mg/kg_suelo'},
    'OM':{'contenido':3.5,'unidades':'%'}, 
    'S':{'contenido':0,'unidades':'%'}, 
    'B':{'contenido':0,'unidades':'%'}   
    }
    #DOSIFICACIO'N de nutrientes necesarios para mantenimiento y producci�n 
    #en 4 etapas                 
    fraccion_consumo = {
    'Ca':[0.35,0.30,0.15,0.20],
    'Mg':[0.35,0.35,0.15,0.15],
    'K':[0.05,0.05,0.45,0.45],
    'P':[0.25,0.25,0.25,0.25],
    'N':[0.25,0.35,0.20,0.20], 
    'Fe':[0.25,0.25,0.25,0.25],
    'Mn':[0.05,0.25,0.35,0.35],
    'Cu':[0.05,0.25,0.35,0.35],
    'Zn':[0.05,0.25,0.35,0.35],
    'OM':[0.05,0.25,0.35,0.35], 
    'S':[0.25,0.25,0.25,0.25], 
    'B':[0.05,0.25,0.35,0.35]   
    }
    
    #DOSIFICACIO'N de nutrientes necesarios para construcci�n de suelo 
    #en 4 etapas (por ejemplo, si desea construir en 5 a�os, cada a�o tendr� 20%, 
    #cada etapa tendr� 5%), colocar 0 si no se desea construir
    fraccion_construccion={
    'Ca':[0.0,0.0,0.0,0.0],
    'Mg':[0.01,0.01,0.01,0.01],
    'K':[0.01,0.01,0.01,0.01],
    'P':[0.01,0.01,0.01,0.01],
    'N':[0.0,0.0,0.0,0.0],
    'Fe':[0.0,0.0,0.0,0.0],
    'Mn':[0.0,0.0,0.0,0.0],
    'Cu':[0.0,0.0,0.0,0.0],
    'Zn':[0.0,0.0,0.0,0.0],
    'OM':[0.0,0.0,0.0,0.0],
    'S':[0.01,0.01,0.01,0.01],
    'B':[0.0,0.0,0.0,0.0]
    }
    #for k,v in fraccion_construccion.items():
    #    fraccion_construccion[k]=[1.0,0,0,0]

    #kilogramos estimados de nutrientes depleted por kg de producción 
    def calcular_necesidad_produccion(produccion):
        nut_produccion={  #kg/kg
        'Ca':4.26/1000,#bot32.   wintgens para robusta menciona 'CaO':5.4, carvajal 4.1kg/1255kgv
        'Mg':2.26/1000,#bot32.   wintgens para robusta menciona 'MgO':4.2, carvajal 4.2kg/1255kgv
        'K': 36.92/1000,#bot32.   wintgens para robusta menciona K2O:44, carvajal 43.3kg/1255kgv
        'P':2.26/1000,#bot32.    wintgens para robusta menciona 'P2O5',6.1, carvajal 3.3 kg/1255kgv
        'N':30.94/1000,#bot32  (wintgens para robusta menciona 33.4kg/1000kg_verde), carvajal 37 kg/1255kgv
        'S':1.21/1000,#, #bot32,  carvajal menciona 3.1kg/1255kgv
        'Fe':107.29e-3/1000,#bot32 
        'Mn':61.36e-3/1000,#bot32 
        'Cu':33.02e-3/1000,#bot32 
        'Zn':17.76e-3/1000,#bot32 
        'B':49.79e-3/1000#bot32        
        }
        for k,v in nut_produccion.items():
            nut_produccion[k]=v*produccion
        return(nut_produccion)

    #kg_nutrientes/hectarea para mantenimiento (sin considerar producci�n)
    def calcular_necesidad_mantenimiento(arb_ha):
        nut_mantenimiento_ha={
            'Ca':(11.8+7.5+23.6)/1345*arb_ha, #carvajal para 1345 arb/ha 1255kgv
            'Mg':(2.8+4.2+8.5)/1345*arb_ha, #carvajal para 1345 arb/ha 1255kgv
            'K': (32.9+23.9+56.8)/1345*arb_ha,#carvajal para 1345 arb/ha 1255kgv
            'P':(2.8+2.5+12.6)/1345*arb_ha,#carvajal para 1345 arb/ha 1255kgv
            'N':(19.3+17.9+66.4)/1345*arb_ha,#carvajal para 1345 arb/ha 1255kgv
            'S':(2.8+1.5+3.5)/1345*arb_ha, #carvajal para 1345 arb/ha 1255kgv
            'Fe':0,
            'Mn':0,
            'Cu':0,
            'Zn':0,
            'B':0        
            }
        return(nut_mantenimiento_ha)

    def calcular_aporte(a,nut_lista,cantidades,fraccion_aprovechada):
        aporte_acumulado_temp=dict.fromkeys(nut_lista,0.)
        for k in nut_lista:  
            for abono in a.keys():
                aporte_acumulado_temp[k]+=(a[abono][k]*cantidades[abono]*
                                        fraccion_aprovechada)
        return(aporte_acumulado_temp)
        
    def calcular_factor(aporte_acumulado_temp,aporte_necesario):
        factormax=0
        mezcla_completa=1
        nut_critico = ""
        for elemento, aporte in aporte_acumulado_temp.items():
            if aporte>0:
                factor=aporte_necesario[elemento]/aporte
                if factor>factormax:
                    factormax=factor
            else:
                factormax=1e6
                break
        return factormax

    def calcular_costo(cantidad_t,costo_kg):
        costo =0
        for abono in cantidad_t:
            costo += costo_kg[abono]*cantidad_t[abono] 
        return(costo)

    nut_lista = ["N","P","K","Ca","Mg","S","Zn","B","Fe","Mn","Cu"]

    #ingrese los resultados de estudios de suelos con sus unidades
    contenido_existente={
    'Ca':{'contenido':cada,'unidades':'cmol+/kg_suelo'},
    'Mg':{'contenido':mgda,'unidades':'cmol+/kg_suelo'},
    'K':{'contenido':kda,'unidades':'cmol+/kg_suelo'},
    'N':{'contenido':nda,'unidades':'mg/L'}, 
    'P':{'contenido':pda,'unidades':'mg/kg_suelo'},
    'Fe':{'contenido':feda,'unidades':'mg/kg_suelo'},
    'Mn':{'contenido':mnda,'unidades':'mg/kg_suelo'},
    'Cu':{'contenido':cuda,'unidades':'mg/kg_suelo'},
    'Zn':{'contenido':znda,'unidades':'mg/kg_suelo'},
    'S':{'contenido':sda,'unidades':'%'}  ,  
    'B':{'contenido':bda,'unidades':'%'}                          
    }

    def calcular_aporte_aplicacion(aporte_construccion,
                            aporte_consumo,fraccion_construccion,
                            fraccion_consumo,n_aplicacion):
        aporte_necesario={}
        for k in nut_lista:
            f_constru=fraccion_construccion[k][n_aplicacion]
            f_consumo=fraccion_consumo[k][n_aplicacion]
            aporte_necesario[k]=(aporte_construccion[k]*f_constru+
                                aporte_consumo[k]*f_consumo)
        #mod decimales
        #aporte_necesario[k]=round(aporte_necesario[k],2) no funciona

        return(aporte_necesario) 


    def calcular_CaCO3(cantidad_t,a_CaCO3):
        CaCO3_total=0
        for abono in cantidad_t:
            CaCO3_total += a_CaCO3[abono]*cantidad_t[abono] 
        CaCO3_total = max(0,CaCO3_total)
        return(CaCO3_total)

    def main():
        factor=1
        costo_abono=10000000
        costo_CaCO3=10000000
        
        nut_mantenimiento_ha=calcular_necesidad_mantenimiento(arb_ha)

        can_temp= {}
        aporte_construccion, aporte_consumo = calcular_aporte_necesario(nut_lista,
            contenido_sugerido,contenido_existente, produccion_kg_ha, arb_ha, 
            densidad,espesor
            )
        aporte_necesario ={}
        cantidad_aplicaciones=4
        costo_anual = 0
        for n_aplicacion in range(cantidad_aplicaciones):
            aporte_necesario = calcular_aporte_aplicacion(aporte_construccion,
                            aporte_consumo,fraccion_construccion,
                            fraccion_consumo,n_aplicacion)
            cantidades = dict.fromkeys(a,100)
            costo=1.e5

            costo_anterior=0.
            i=0
            while costo!= costo_anterior and i<1000:
                i+=1
                costo_anterior=costo
                for abo_cam in a.keys():
                    for k in cantidades:
                        can_temp[k]=cantidades[k]
                    for cambio in [-1,1]: 
                        can_temp[abo_cam]=max(0.,cantidades[abo_cam]+cambio)
                        aporte_acumulado_t=calcular_aporte(a,nut_lista,can_temp,
                                                        fraccion_aprovechada)
                        factor_t = calcular_factor(aporte_acumulado_t, aporte_necesario)
                        CaCO3_total= calcular_CaCO3(can_temp,a_CaCO3)
                        costo_CaCO3_t=CaCO3_total*costo_CaCO3_kg*factor_t
                        costo_abono_t = calcular_costo(can_temp,costo_kg)*factor_t
                        
                        if costo_CaCO3_t + costo_abono_t < costo:
                            costo = costo_CaCO3_t + costo_abono_t
                            costo_abono = costo_abono_t
                            costo_CaCO3 = costo_CaCO3_t
                            CaCO3 = CaCO3_total
                            factor=factor_t
                            for k in cantidades:
                                cantidades[k]=can_temp[k]          
            kg_total=0
            for k,v in cantidades.items():
                kg_total += v*factor
                cantidades[k] *= factor 

                #mod
                cantidades[k] = round(cantidades[k],2)
                kg_total=round(kg_total,2)
                lista1.append(cantidades[k])
                lista2.append(k)
                
            #mod
            costo_abono=round(costo_abono,2)
            lista3.append(kg_total)
            lista4.append(costo_abono)
            lista5.append(costo_CaCO3)
            
            print("------------------------------------------------")
            print("APLICACION ",n_aplicacion)
            print("para lograr el aporte necesario:")
            print(aporte_necesario)
            print()
            print("aplicar: ",kg_total," kg/ha de una mezcla con las",
                "siguientes cantidades",cantidades)
            print("costo abono",costo_abono," costo neutralizar",costo_CaCO3)
            costo_anual += costo



        print("COSTO ANUAL:",round(costo_anual,2))
        print("")
        print("NOTA IMPORTANTE:")
        print("Estas no son recomendaciones agronomicas, son solo c�lculos matem�ticos")
        print("basados en los datos introducidos a este c�digo, que pudieran servir")
        print("como herramienta de c�lculo durante las decisiones agron�micas")
        print("")

        #mod
        print("lista 1= ", lista1)
        print("lista 2= ", lista2)
        print("lista 3= ", lista3)
        print("lista 4= ", lista4)
        print("lista 5= ", lista5)
        print("prueba= ", mgda)

    main()

    return lista1, lista2, lista3, lista4, lista5

#MOD
# cada,mgda,kda,nda,pda,feda,mnda,cuda,znda,sda,bda, 
# a1_nom_v, a1_cos_v, a1_ca_v, a1_mg_v, a1_k_v, a1_n_v, a1_p_v, a1_fe_v, a1_mn_v, a1_cu_v, a1_zn_v, a1_s_v,a1_b_v,
# a2_nom_v, a2_cos_v, a2_ca_v, a2_mg_v, a2_k_v, a2_n_v, a2_p_v, a2_fe_v, a2_mn_v, a2_cu_v, a2_zn_v, a2_s_v,a2_b_v,
# a3_nom_v, a3_cos_v, a3_ca_v, a3_mg_v, a3_k_v, a3_n_v, a3_p_v, a3_fe_v, a3_mn_v, a3_cu_v, a3_zn_v, a3_s_v,a3_b_v,
# cosca_v, densi_v, espes_v, produ_v, 
# arbha_v, rega_v, polisulfato_v,high_complete_v, nitrabor_v, high_k_v,granumax_s_v,
# hydran_v, colono_v, dap_v, kicerita_v, dolomita_v, micromix_forte_v, enmienda1_v, kcl_v, kmag_v, urea_v,boro_v, magnesamon_v,
# e1_ca_v, e1_mg_v, e1_k_v, e1_n_v, e1_p_v, e1_fe_v, e1_mn_v, e1_zn_v, e1_cu_v, e1_s_v, e1_b_v,
# e2_ca_v, e2_mg_v, e2_k_v, e2_n_v, e2_p_v, e2_fe_v, e2_mn_v, e2_zn_v, e2_cu_v, e2_s_v, e2_b_v,
# e3_ca_v, e3_mg_v, e3_k_v, e3_n_v, e3_p_v, e3_fe_v, e3_mn_v, e3_zn_v, e3_cu_v, e3_s_v, e3_b_v,
# e4_ca_v, e4_mg_v, e4_k_v, e4_n_v, e4_p_v, e4_fe_v, e4_mn_v, e4_zn_v, e4_cu_v, e4_s_v, e4_b_v
#8.57,1.62,0.60,10.8,32.65,178.38,23.85,15.35,23.30,0,0,

# intdata(0.9,0.16,0.06,1.1,3.3,18,2.4,1.5,2.3,0,0,
# 100,1,0,0,0,0,0,0,0,0,0,0,0,200,1,0,0,0,0,0,0,0,0,0,0,0,300,1,0,0,0,0,0,0,0,0,0,0,0,
# 0.10, 1200, 0.2, 1800, 3000, 
# 0.8,0.58,0.6,0.7,0.6,0.5,0.6,0.6,0.5,0.36,0.36,0.8,0.32,0.42,0.59,0.46,16.25,0.37,
# 0.35,0.35,0.05,0.25,0.25,0.25,0.05,0.05,0.05,0.25,0.05,
# 0.30,0.35,0.05,0.25,0.35,0.25,0.25,0.25,0.25,0.25,0.25,
# 0.15,0.15,0.45,0.25,0.20,0.25,0.35,0.35,0.35,0.25,0.35,
# 0.20,0.15,0.45,0.25,0.20,0.25,0.35,0.35,0.35,0.25,0.35)