#!/bin/bash
cd /Users/dayoung/Desktop/test
chmod +x compile.sh

javac -cp ".;src" *.java
java src/Calculator.java
