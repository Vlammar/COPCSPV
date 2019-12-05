#Made by Valentin Jabre
#execute on all .xml files the csp solver
temp=60
cd CSP/runner-dft/runner
for file in ../../../cop_instances/*.xml;
do
	echo $file
        name=$(basename "$file" ".xml")
	echo name
        echo $name
        time (./xcsp3-* -tl $temp $file>../../../solution/$name.txt) &>../../../out/out_csp$name

done


