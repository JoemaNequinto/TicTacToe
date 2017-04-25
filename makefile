build:
	javac -d bin -sourcepath src src/*.java
	jar cfev TicTacToe.jar Main -C bin .

run:
	java -jar TicTacToe.jar

debug-build:
		javac -d bin -sourcepath src src/*.java -g
		jar cfev TicTacToe.jar Main -C bin .