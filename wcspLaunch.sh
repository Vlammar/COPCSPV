#Made by Valentin Jabre
for file in wcsp_instances/*.wcsp
do
	echo Running:
	name=$(basename "$file" ".wcsp")
	echo $name
	toulbar2 -timer=$1 -w=results/sol/$name.sol  wcsp_instances/$name>results/log/$name.log
done
