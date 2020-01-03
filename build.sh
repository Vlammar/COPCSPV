#Made by Valentin Jabre
#build the csp modeler
git submodule update
cp src/Frequences.java CSP/XCSP3-Java-Tools/src/main/java/org/xcsp/modeler/problems
cd CSP/XCSP3-Java-Tools
mvn package -Dmaven.test.skip=true
cd ../../..

