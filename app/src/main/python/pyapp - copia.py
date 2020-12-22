#codigo para determinar la combinación que produce el minimo costo 
#entre un set de posibilidades de fertilizantes, enmiendas
#considerando un set de requerimientos de elementos
#jose.gallardo@utp.ac.pa  octubre/2020

import numpy as np


#factor multiplicador del aporte objetivo
multiplicador=2
#cuantas fracciones del mayor de los abonos puede usarse en el minimo
fracciones=3
#producción estimada
produccion_kg_hcta=1800 #40 quintales/hectarea = 1800 kg/hectarea

kg_mol={'N':0.014,'Mg':0.0243,'P':0.031,'S':0.0321,'K':0.039,'Ca':0.0401,'Mn':0.0549,'Fe':0.0558,'Cu':0.0635,'Zn':0.0654}
eq_mol={'Mg':2,'K':1,'Ca':2}


def printdi(dictionary):
    t=""
    for k,v in dictionary.items():
         t+=k+":"+str(round(v,3))+" "
    print(t)

def equiv(compuesto,peso):
    if compuesto == "P2O5":
        factor= 0.4364
    if compuesto == "K2O":
        factor = 0.8301
        entrok=1
    if compuesto == "CaO":
        factor = 0.7146
    if compuesto == "MgO":
        factor = 0.6031
    if compuesto == "SO4":
        factor = 0.333
    return peso*factor

#función para transformar de diversas unidades a kilogramos por hectarea
def kg_hcta(elemento,contenido):
    densidad=1200 #kg/m3
    espesor=0.2 #m
    x=contenido['contenido']
    u=contenido['unidades']
    kg_suelo_hcta=espesor*10000*densidad #kg/hcta
    if u == 'meq%':
        x *= 10/1000*eq_mol[elemento]*kg_mol[elemento]*kg_suelo_hcta
    if u =='cmol+/kg_suelo':
        x *= kg_mol[elemento]/100*kg_suelo_hcta
    if u =='mg/kg_suelo'or u=='ppm' or u=='ppm(Troug)':
        x *= kg_suelo_hcta/1e6
    if u =='ppm(Olsen-Davin)':
        x *= kg_suelo_hcta/1e6*22/45
    if u =='%':
        x *= kg_suelo_hcta/100
    if u =='mg/L':
        x *= 1e-6*(1000)*(espesor*10000)  #1000 L / metrocubico
    return(x)

#DATA CERO

caval=2
mgval=2
kval=0
nval=0
pval=0
feval=0
mnval=0
cuval=0
znval=0
sval=0
bval=0


#DATA DESDE LA APP

def intdata (cada,mgda,kda,nda,pda,feda,mnda,cuda,znda,sda,bda):
    
    global caval

    caval= int(cada)
    mgval= int(mgda)
    kval= int(kda)
    nval= int(nda)
    pval= int(pda)
    feval= int(feda)
    mnval= int(mnda)
    cuval= int(cuda)
    znval= int(znda)
    sval= int(sda)
    bval= int(bda)
     
   
    
    return caval

   
#intdata (caval, mgval, kval, nval, pval, feval, mnval, cuval, znval, sval, bval)    
    
#ingrese los resultados de estudios de suelos con sus unidades
 
contenido_existente={
'Ca':{'contenido':5,'unidades':'cmol+/kg_suelo'},
'Mg':{'contenido':3,'unidades':'cmol+/kg_suelo'},
'K':{'contenido':0.60,'unidades':'cmol+/kg_suelo'},
'N':{'contenido':10.8,'unidades':'mg/L'}, 
'P':{'contenido':32.65,'unidades':'mg/kg_suelo'},
'Fe':{'contenido':178.38,'unidades':'mg/kg_suelo'},
'Mn':{'contenido':23.85,'unidades':'mg/kg_suelo'},
'Cu':{'contenido':15.35,'unidades':'mg/kg_suelo'},
'Zn':{'contenido':23.30,'unidades':'mg/kg_suelo'},
'S':{'contenido':0,'unidades':'%'}  ,  
'B':{'contenido':0,'unidades':'%'}                          
}

#sugerencias para Ca, Mg, K, P, N,  OM 
#para suelos con 28% contenido de finos 
#(funciona bien para 18 a 28% finos). 
#Fuente Forestier, tomado de Wintgens tabla 8.1.2
#y sugerencias Fe, Mn, Cu, Zn Fuente Fertiliza
#aún no se cuenta con recomendación ni estudios de suelos para boro y azufre
contenido_sugerido={
'Ca':{'contenido':2.61,'unidades':'meq%'},#se utilizó el minimo recomendado, 
#ya que la recomendación media de calcio puede resultar en costos insostenibles
'Mg':{'contenido':0.45,'unidades':'meq%'},#se utilizó el minimo recomendado, 
#ya que la recomendación media de calcio puede resultar en costos insostenibles
'K':{'contenido':0.35,'unidades':'meq%'},
'P':{'contenido':equiv('P2O5',13),'unidades':'ppm(Troug)'},
'N':{'contenido':1.74,'unidades':'mg/L'}, 
'Fe':{'contenido':10,'unidades':'mg/kg_suelo'},
'Mn':{'contenido':5,'unidades':'mg/kg_suelo'},
'Cu':{'contenido':2,'unidades':'mg/kg_suelo'},
'Zn':{'contenido':3,'unidades':'mg/kg_suelo'},
'OM':{'contenido':3.5,'unidades':'%'}, 
'S':{'contenido':0,'unidades':'%'}, 
'B':{'contenido':0,'unidades':'%'}   
}


#dosificacion_non-construccion={
#'Ca':[0.05,0.25,0.35,0.35],
#'Mg':[0.05,0.25,0.35,0.35],
#'K':[0.05,0.25,0.35,0.35],
#'P':[0.05,0.25,0.35,0.35],
#'N':[0.05,0.25,0.35,0.35], 
#'Fe':[0.05,0.25,0.35,0.35],
#'Mn':[0.05,0.25,0.35,0.35],
#'Cu':[0.05,0.25,0.35,0.35],
#'Zn':[0.05,0.25,0.35,0.35],
#'OM':[0.05,0.25,0.35,0.35], 
#'S':[0.05,0.25,0.35,0.35], 
#'B':[0.05,0.25,0.35,0.35]   
#}


#kilogramos estimados de nutrientes depleted por kg de producción
#fuente: 
nutriente_produccion={
'Ca':4.26/1000,#bot32.   wintgens para robusta menciona 'CaO':5.4
'Mg':2.26/1000,#bot32.   wintgens para robusta menciona 'MgO':4.2
'K': 36.92/1000,#bot32.   wintgens para robusta menciona K2O:44
'P':2.26/1000,#bot32.    wintgens para robusta menciona 'P2O5',6.1
'N':30.94/1000,#bot32  (wintgens para robusta menciona 33.4kg/1000kg_verde)
'S':1.21/1000,#, #bot32 
'Fe':107.29e-3/1000,#bot32 
'Mn':61.36e-3/1000,#bot32 
'Cu':33.02e-3/1000,#bot32 
'Zn':17.76e-3/1000,#bot32 
'B':49.79e-3/1000#bot32        
}

#kilogramos estimados de nutrientes por hectarea para mantenimiento
#sin el consumo de ripe fruits
#fuente: 
nutriente_mantenimiento_ha={
'Ca':equiv('CaO',52.5-4.6),
'Mg':equiv('MgO',25.9-5.5),
'K': equiv('K2O',149.8-41.4),
'P':equiv('P2O5',38.4-5.9),
'N':112.1-29.5,
'S':1.21/1000, #bot32 
'Fe':0.10729/1000,#bot32 
'Mn':0.06136/1000,#bot32 
'Cu':0.03302/1000,#bot32 
'Zn':0.01776/1000,#bot32 
'B':0.04979/1000#bot32        
}


#estimación del aporte necesario para cubrir las deficiencias y 
#suplir lo que va a consumir la produccion
aporte_necesario={}
for nutriente, cant in nutriente_produccion.items():    
    sugerido = kg_hcta(nutriente,contenido_sugerido[nutriente])
    existencia = kg_hcta(nutriente,contenido_existente[nutriente])
    deficiencia = max(0,sugerido-existencia)
    necesidad_hcta = (cant*produccion_kg_hcta+deficiencia)*multiplicador
    print(nutriente,cant*produccion_kg_hcta,deficiencia,necesidad_hcta)    
    aporte_necesario[nutriente]=necesidad_hcta


#Composición de cada tipo de abono (en porcentaje)
a={
"Nitrabor":{"N":15,"P2O5":0,"K2O":0,"CaO":26,"MgO":0,"SO4":0,
"Zn":0,"B":0.3,"Fe":0,"Mn":0,"Cu":0},

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

"Kicerita":{"N":0,"P2O5":0,"K2O":0,"CaO":0,"MgO":14.9,"SO4":62,
"Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0},

"Dolomita":{"N":0,"P2O5":0,"K2O":0,"CaO":32.4,"MgO":18.2,"SO4":0,
"Zn":0,"B":0,"Fe":0,"Mn":0,"Cu":0},

"Micromix_forte":{"N":3,"P2O5":3,"K2O":0,"CaO":2,"MgO":4,"SO4":15,
"Zn":12,"B":1.5,"Fe":3,"Mn":1,"Cu":1}
}
#modificar contenidos para considerar que son porcentajes
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
    a[abono]["S"]=equiv("SO4",a[abono]["SO4"])
    del a[abono]["SO4"]

costo_kg={
"Nitrabor":0.7,
"Rega":0.8,
"Polisulfato":0.6, 
"High_Complete":0.6,
"High_K":0.6,
"Granumax_S":0.5,
"Hydran":0.6,
"Colono_15-5-20":0.6, 
"DAP":0.6, 
"Kicerita":0.6,
"Dolomita":0.6,
"Micromix_forte":0.8
}





aporte_acumulado=dict.fromkeys(nutriente_produccion,0.)     
aporte_acumulado_temp=dict.fromkeys(nutriente_produccion,0.)     
print
cantidades=dict.fromkeys(a,100)
cant_efic=dict.fromkeys(a,100)
cant_temp=dict.fromkeys(a,100)
aporte_efic=dict.fromkeys(nutriente_produccion,0.) 


def factor():
    global aporte_acumulado
    global aporte_acumulado_temp
    global aporte_necesario
    factormax=0
    mezcla_completa=1
    nutriente_critico = ""
    for elemento, aporte in aporte_acumulado_temp.items():
        if aporte>0:
            factor=aporte_necesario[elemento]/aporte
            if factor>factormax:
                factormax=factor
                nutriente_critico = elemento
        else:
            mezcla_completa=0
           
            break
    return factormax, mezcla_completa, nutriente_critico

def ciclar(abono_cambiando):
    global costo_minimo
    global costo_efic
    global iteraciones
    global cantidades
    global can_ant
    global costo_unitario
    global costo_unitario_efic
    global a
    global factor_efec
    global aporte_acumulado
    global aporte_acumulado_temp
    global abonos
    global nabonos
    global iterant
    global aporte_necesario
    cambio_costo = 0
    for k,v in cantidades.items():
            cant_temp[k]=v
    for kg in range(-1,2,1):
        cant_temp[abono_cambiando]=max(0.,cantidades[abono_cambiando]+kg)
        costo_unitario_temp=0.        
        for abono in a.keys():
            costo_unitario_temp += costo_kg[abono]*cant_temp[abono]        
        for k in aporte_acumulado.keys():  
            aporte_acumulado_temp[k]=0.
            for abono in a.keys():
                aporte_acumulado_temp[k]+=a[abono][k]*cant_temp[abono]
        iteraciones += 1        
        fact, mezcla_completa, nutriente_critico = factor()

        if mezcla_completa==1:     
            costo_total = costo_unitario_temp*fact         
            for k,v in aporte_acumulado_temp.items():
                aporte_factorado[k] = v*fact     
            #print(abono_cambiando,cantidades[abono_cambiando],cant_temp[abono_cambiando],
                                           #np.round(fact,2),np.round(costo_total,2),np.round(costo_minimo,2))
            #print(cantidades)
            #print(cant_temp)
            #print(cantidades)
            if costo_total<costo_minimo:    
                costo_minimo=costo_total
                cambio_costo =1
                factor_efec=fact
                print()
                print("-----------------------------------")
                print("necesidad estimada (kg/ha): ",end='')
                printdi(aporte_necesario)
                print("            aporte (kg/ha): ",end='')
                printdi(aporte_factorado)                 
                print("cantidad de abonos (kg/ha):",end='')
                for k,v in cantidades.items():
                    print(k, round(v*fact,0), end='; ') 
                print(cantidades)
                print("costo",costo_total, "  nutriente critico:",nutriente_critico) 
                
                cant_efic=cant_temp[abono_cambiando]

        if iteraciones-iterant > 0.01*posibiter:
            iterant=iteraciones
            #print(int(round(iteraciones/posibiter*100,0)),end='% - ')
    if cambio_costo == 1:   
        cantidades[abono_cambiando]=cant_efic
    
entrok=0
costo_minimo=1e32
costo_efic=1e32
nabonos =len(a)
abonos = list(a.keys())
iteraciones=0
iterant=0
factor_efec=0
posibiter= (fracciones+1)**nabonos
costo_unitario=0
for abono in a.keys():
    costo_unitario+=costo_kg[abono]
costo_unitario_efic=costo_unitario
print(costo_unitario)
aporte_factorado={}

for k in aporte_acumulado.keys():  
    for abono in a.keys():
        contenido_nut=a[abono]     
        if contenido_nut[k] >0 :
            aporte_acumulado[k]=aporte_acumulado[k]+contenido_nut[k]*cantidades[abono]
    aporte_acumulado_temp[k]=aporte_acumulado[k]
    aporte_efic[k]=aporte_acumulado[k]


for i in range(1000):
    for nombre_abono in a.keys():
        ciclar(nombre_abono)
        for k,v in cantidades.items():
            cant_temp[k]=v
    print(i,factor_efec,"xxx",np.round(costo_minimo,2), end=" ")
print("")

lista1=[]
lista2=[]
lista3=[]

for k,v in cantidades.items():
    print(k,round(v*factor_efec,2),"kg/hcta")
    lista1.append(k)
    lista2.append(round(v*factor_efec,2))


#ALMACENAJE DE INFORMACION
    
n00=str(lista1[0])
v00=str(lista2[0])

n01=str(lista1[1])
v01=str(lista2[1])

n02=str(lista1[2])
v02=str(lista2[2])

n03=str(lista1[3])
v03=str(lista2[3])

n04=str(lista1[4])
v04=str(lista2[4])

n05=str(lista1[5])
v05=str(lista2[5])

n06=str(lista1[6])
v06=str(lista2[6])

n07=str(lista1[7])
v07=str(lista2[7])

n08=str(lista1[8])
v08=str(lista2[8])

n09=str(lista1[9])
v09=str(lista2[9])

n10=str(lista1[10])
v10=str(lista2[10])

n11=str(lista1[11])
v11=str(lista2[11])


#ENVIO DE INFORMACION



def nv00():
    
     return n00+": "+v00+ " kg/ha"

def nv01():
    
    return n01+": "+v01+ " kg/ha"

def nv02():
    
    return n02+": "+v02+ " kg/ha"

def nv03():
    
    return n03+": "+v03+ " kg/ha"

def nv04():
    
    return n04+": "+v04+ " kg/ha"

def nv05():
    
    return n05+": "+v05+ " kg/ha"

def nv06():
    
    return n06+": "+v06+ " kg/ha"

def nv07():
    
    return n07+": "+v07+ " kg/ha"

def nv08():
    
    return n08+": "+v08+ " kg/ha"

def nv09():
    
    return n09+": "+v09+ " kg/ha"

def nv10():
    
    return n10+": "+v10+ " kg/ha"

def nv11():
    
    return n11+": "+v11+ " kg/ha"
   


print("")
print("NOTA IMPORTANTE:")
print("Estas no son recomendaciones agronómicas, son solo cálculos matemáticos")
print("basados en los datos introducidos a este código, que pudiera servir")
print("como herramienta de cálculo durante las decisiones agronómicas")
print("")

print(n00+" "+v00)
print(caval) 
print(mgval) 






