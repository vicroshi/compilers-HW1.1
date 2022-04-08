all:
	javac -d out -sourcepath src src/Main.java
	java -classpath out Main
clean:
	rm -rf out/*.class