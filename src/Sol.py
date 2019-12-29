import math

def printsol(f,n,mode,verbose,markdown):
    model=f.readline()
    if "cop" in model:
        printCop(f,n,mode,verbose,markdown)
    else:
        printWcsp(f,n)

def printWcsp(f,n):
    print("Todo print Wcsp :)")
    pass

"""
f=fichier a lire
n=nombre antennes
verbose=affiche toutes les infos pour chaque mode ainsi que tous les resultats(debug)
markdown=produit une sortie sous la forme d un morceau de ligne de tableau markdown
"""
def printCop(f,n,mode,verbose,markdown):
    sol=[0]*n
    l=f.readline()
    nbfreqcalc=0
    maxfreq=0
    minfreq=math.inf
    nbfreq=" Defined only for m1"
    while "<values>" not in l:
            l=f.readline()
            #on verifie si il y a une solution
            if l=="\n" or l=="":
                #print("Pas de solution")
                print("N/A|N/A|N/A")
                exit(0)

    s=l.split("<values>")[1].split("</values>")[0]
    cpt=0
    for m in s.split():

        if(cpt>=n):
            nbfreq=int(m)
        else:
            if(int(m)not in sol):
                nbfreqcalc+=1
                if(maxfreq<int(m)):
                    maxfreq=int(m)
                if(minfreq>int(m)):
                    minfreq=int(m)
            sol[cpt]=int(m)
        cpt+=1

    if(markdown):
        print("{}".format(nbfreqcalc),end=" | ")
        print("{}".format(maxfreq),end=" | ")
        print("{}".format(maxfreq-minfreq))

    elif(verbose):
        print("nbfreq mesure = {}".format(nbfreqcalc))
        print("maxfreq  = {}".format(maxfreq))
        print("minfreq  = {}".format(minfreq))
        print("deltafreq  = {}".format(maxfreq-minfreq))

    else:
        if(mode=="m1"):
            print("m1:")
            print("nbfreq differente = {}".format(nbfreq))
            print("verification nbfreq mesure = {}".format(nbfreqcalc))
        elif(mode=="m2"):
            print("m2:")
            print("maxfreq  = {}".format(maxfreq))
            print("minfreq  = {}".format(minfreq))
        elif(mode=="m3"):
            print("m3:")
            print("deltafreq  = {}".format(maxfreq-minfreq))
        elif(mode=="default"):
            print("An error happened, check if cop instance file name contains m1 or m2 or m3 in their names")


        else:
            print("Error")
    if(verbose):
        print("Frequences allouees")
        print("Emission")
        print("\n")
        for b in range(k):
            print("Antenne E{} valeur{} frequence{}".format(b,sol[b],sol[b]))
        print("Reception")
        for b in range(k):
            print("Antenne R{} valeur{} frequence{}".format(b,sol[k+b],sol[k+b]))

import sys
import os
verbose=False
markdown=False
if(sys.argv[1]=="-v"):
    verbose=True
    path=sys.argv[2]
elif(sys.argv[1]=="-m"):
    markdown=True
    path=sys.argv[2]
else:
    path=sys.argv[1]


mode="default"
if("m1" in path):
    mode="m1"
if("m2" in path):
    mode="m2"
if("m3" in path):
    mode="m3"
if "celar" in path :
    s=(path.split("/")[1]).split(".txt")[0].split("_")
    k=int(s[1])
    n=2*k
elif "test" in path :
    k=3
    n=2*k

f = open(path, "r")

if(markdown):
    name=path.split("/")[1].split(".txt")[0]
    out=open("out/out_cop_"+name,"r")
    l=out.readline()
    while "real" not in l:
            l=out.readline()
            time=l.split("\t")[1].split("\n")[0]
    out.close()
    f2=open("solution/"+name+".txt","r")
    l=f2.readline()
    while ("OPTIMUM FOUND" not in l) and("SATISFIABLE" not in l) and("UNKNOWN" not in l) :
        l=f2.readline()
    opt=l.split(" ")[1].split("\n")[0]
    f2.close()
    #print("Mode|Nom|Temps|Opt|nbfreq|maxfreq|deltafreq")
    #print("--|----|----|---|-----|-----|-----")
    print("{}|{}|{}|{}|".format(mode,name.split(mode+"-")[1],time,opt),end="")
printsol(f,n,mode,verbose,markdown)
f.close()
