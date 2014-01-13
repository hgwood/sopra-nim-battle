@echo off

set ARCHIVE_NAME=battle-2014-notfound.zip

del %ARCHIVE_NAME%
if exist sources (del /S /Q /F sources)

git clone https://github.com/hgwood/battle-sopra-2014.git sources

set PATH=%CD%\tools\apache-maven-3.1.1\bin;%PATH%
set JAVA_HOME=%CD%\tools\jdk1.7.0_40
set M2_HOME=%CD%\tools\apache-maven-3.1.1

cd sources
call mvn clean install -s ..\packaging-settings.xml
cd ..

7za a %ARCHIVE_NAME% -mx0 setenv.bat m2repo tools sources workspace