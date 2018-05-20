idlj -fall Chat.idl


javac *.java Chat\*.java

start orbd -ORBInitialPort 1050 -ORBInitialHost localhost
timeout /t 2
start java ChatServer -ORBInitialPort 1050
timeout /t 2
start java ChatClient Rachel -ORBInitialPort 1050
start java ChatClient Sarah -ORBInitialPort 1050
start java ChatClient Herbie -ORBInitialPort 1050
