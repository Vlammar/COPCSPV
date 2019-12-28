#Made by Valentin Jabre
#generate cop model
cd cop_instances/ && rm *.xml && cd ..

#Pour etre sur que le .jar est bien genere
./build.sh
./generate_cop.sh data/test
./copLaunch.sh

#print for solutions

./solutions_cop.sh
