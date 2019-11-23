#Made by Valentin Jabre
#generate .xml csp files using the xcsp3 modeler
#You need to run ./build.sh before in order to make sure the Schur model is created

data_path=$1

for data in data_path/*.JSON
do
	java -jar CSP/XCSP3-Java-Tools-master/target/xcsp3-compiler-1.1.1-SNAPSHOT.jar org.xcsp.modeler.problems.Frequences -data=$data
	mv Frequences* csp_instances;
done
