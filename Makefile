APP = Application
JAR = Calc
ASSETS = assets
DEPS = calc giava

comp: 
	javac -d . *.java

run:
	java ${APP}

clean:
	find . -name "*.class" -type f -delete

crc: comp run clean

doc:
	javadoc -d doc -sourcepath . -subpackages giava:calc -windowtitle "Documentazione Calc"  -doctitle "Calc doc"

deldoc:
	rm -r doc

jarcomp: comp
	jar cvfe ${JAR}.jar ${APP} *.class ${DEPS} ${ASSETS}

jar: jarcomp clean

jarun:
	java -jar ${JAR}.jar