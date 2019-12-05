
#Made by Valentin Jabre
#generate .xml csp files using the xcsp3 modeler
#You need to run ./build.sh before in order to make sure the Schur model is created


for data in $1/*
do
	echo $data
	java -jar CSP/XCSP3-Java-Tools/target/xcsp3-compiler-1.1.1-SNAPSHOT.jar org.xcsp.modeler.problems.Frequences -data=$data
	mv Frequences* cop_instances;
done
