build:
	javac -d bin -sourcepath src src/*.java
	jar cfev TicTacToe.jar Main -C bin .

debug-build:
		javac -d bin -sourcepath src src/*.java -g
		jar cfev TicTacToe.jar Main -C bin .