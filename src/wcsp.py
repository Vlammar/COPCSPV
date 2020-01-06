import json

class Station:
    def __init__(self, num,region,delta,emetteur,recepteur):
        self.num = num
        self.region = region
        self.delta = delta
        self.emetteur = emetteur
        self.recepteur = recepteur

class Interference :
    def __init__(self, x,y,Delta):
        self.x = x
        self.y = y
        self.Delta = Delta



class Liaison :
    def __init__(self, x,y):
        self.x = x
        self.y = y



path="data/donnees_wcsp/celar_50_7_10_5_0.800000_0.json"
name=path.split("data/donnees_wcsp/")[1]
values=name.split("celar_")[1].split(".json")[0].split("_")
n=2*int(values[0])
nbregions=int(values[1])

with open(path, "r") as content_file:
    content = content_file.read()
y=json.loads(content)
"""print("liste des clefs:\n")
for key in y.keys():
    print(key)

print("\n")
print("Valeurs limite nb frequence utilise par region:\n")
for i in range(nbregions):
        print("Region {} nb max = {}".format(i,y["regions"][i]))

print("\nListe liaisons:\n")
for l in y["liaisons"]:
    print(l)

print("\nListe interferences:\n")
for l in y["interferences"]:
    print(l)

print("\nListe stations:\n")
for l in y["stations"]:
    print(l)
"""
maximum=0
maxs=[]
for l in y["stations"]:
    maxloc=0
    for freq in l["emetteur"]:
        if freq>maximum:
            maximum=freq
        if freq>maxloc:
            maxloc=freq
    maxs.append(maxloc)
    maxloc=0
    for freq in l["recepteur"]:
        if freq>maximum:
            maximum=freq
        if freq>maxloc:
            maxloc=freq
    maxs.append(maxloc)

dommax=2*maximum

costfunctionNB=69
UB=70 #interferences.length + connection.length + 1



print("Allocation frequence {} {} {} {}".format(n,dommax,costfunctionNB,UB))

for var in maxs:
    print(var+1,end=" ")

idx=0
for l in y["stations"]:

    print("{} ".format(100),end=" ")
    for freq in l["emetteur"]:

        print("2 {} {} -1 = 0 0 ".format(idx,freq),end=" ")
    print("-1 sdij 0 10000")
    idx+=1
    for freq in l["recepteur"]:
        print("{} = {}".format(idx,freq),end=" ")
    print("sdij 0 10000")
    idx+=1

