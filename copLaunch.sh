#Made by Valentin Jabre
#execute on all .xml files the csp solver
temp=$1
cd CSP/runner-dft/runner
for file in ../../../cop_instances/*.xml;
do
	echo Running:	
        name=$(basename "$file" ".xml")
        echo $name
        time (./xcsp3-* -tl $temp $file>../../../solution/$name.txt) &>../../../out/out_cop_$name

done


