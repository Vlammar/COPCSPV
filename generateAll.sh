#Made by Valentin Jabre
DIRS=["cop_instances","out","results","solution"]

#Initialisation
for dir in DIRS
do
	if [ ! -d $dir ]
	then
		mkdir $dir
	fi
done

#Nettoyage
for dir in DIRS
do
	if [ ! "$(ls -A $dir)" ]
	then
		cd $dir
		rm *
		cd ..
	fi
done

#Pour etre sur que le .jar est bien genere
./build.sh

#Generation des modeles cop
./generate_cop.sh data/test
#On resouds les modeles cop 
./copLaunch.sh 12

#Affichage des solutions cop, cree un fichier texte contenant les resultats sous forme d un tableau markdown
./solutions_cop.sh
