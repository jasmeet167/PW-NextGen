@echo off
rem ====================
rem Modifications: T0196
rem ====================
SET JAVA_HOME=%~dp0..\..\software\jdk-1.8.0
SET M2_HOME=%~dp0..\..\software\maven
SET M2=%M2_HOME%\bin
SET MAVEN_OPTS=-Xmx768m -Xms256m
call "%M2%\mvn" -o -s "%~dp0settings.xml" "-Dmaven.repo.local=%~dp0../../software/.m2/repository" -f "%~dp0..\pom.xml" -P default,client,warlogger,earconfig install
call "%M2%\mvn" -o -s "%~dp0settings.xml" "-Dmaven.repo.local=%~dp0../../software/.m2/repository" -f "%~dp0..\..\xgws-ear\pom.xml" -P default,earwma,earwmaann,earwmapayout,earwmatrd,earwmaul,earconfig clean install
if "%NO_PAUSE%"=="" pause