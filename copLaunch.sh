#Made by Valentin Jabre
#execute on all .xml files the csp solver
temp=600
cd CSP/runner-dft/runner
for f in ../../../csp_instances/*.xml;
do
        name=$(basename "$f" ".xml")
        echo $name
        time (./xcsp3-* -tl $temp $f>../../../solution/$name.txt) &>../../../out/out_csp$name

done


