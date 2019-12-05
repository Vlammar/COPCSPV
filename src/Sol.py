def printsol(f,k,n):
    #sol contient les numeros de boite des variables i


    model=f.readline()
    if "cop" in model:
        printCop(f,k,n)
    else:
        printWcsp(f,k,n)


def printWcsp(f,k,n):
    print("Todo print Wcsp :)")
    pass

def printCop(f,k,n):
    sol=[0]*n
    l=f.readline()
    while "<values>" not in l:
            l=f.readline()
            #on verifie si il y a une solution
            if l=="\n" or l=="":
                print("Pas de solution")
                exit(0)

    s=l.split("<values>")[1].split("</values>")[0]
    cpt=0
    for m in s.split():
        sol[cpt]=int(m)
        cpt+=1
    """for boite in range(k):
        #print("Boite "+str(boite)+" :")
        for i in range(0,n):
            if sol[i]==boite:
                print(i+1,end=" ")
        print()
"""
    print("Frequences allouees")
    print("Emission")
    print("TODO lecture de la frequence car 0 1 2ca va bien 30s")
    for b in range(k):
        print("Antenne E{} valeur{} frequence{}".format(b,sol[b],sol[b]))
    print("Reception")
    for b in range(k):
        print("Antenne R{} valeur{} frequence{}".format(b,sol[k+b],sol[k+b]))

import sys
import os
print(sys.argv)
path=sys.argv[1]
print(path)
k=3
n=2*k
if "celar" in path :
    s=path.split("Frequences-celar")[1].split(".txt")[0].split("_")
    k=int(s[0])
    n=2*k
f = open(path, "r")
printsol(f,k,n)

