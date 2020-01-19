@ECHO OFF
ECHO ========================================
ECHO      Auto Clean And Package
ECHO =========================================
::设置版本start
del version.properties
set filename=cdWriterVersion=1.%date:~5,2%.%date:~8,2%
::%time:~0,2%%time:~3,2%%time:~6,2%
set "filename=%filename: =0%"
echo %filename%>>version.properties
::设置版本end
set javaPath=D:\jdk8\bin
set projectDirectory=E:\Project\cdwriter
set mavenHome=E:\AboutMe\MyTools\apache-maven-3.5.4-bin\apache-maven-3.5.4
set mvnCall=%javaPath%\java.exe -Dmaven.multiModuleProjectDirectory=%projectDirectory% -Dmaven.home=%mavenHome% -Dclassworlds.conf=%mavenHome%\bin\m2.conf -javaagent:D:\IntelliJIDEA\lib\idea_rt.jar=58046:D:\IntelliJIDEA\bin -Dfile.encoding=UTF-8 -classpath %mavenHome%\boot\plexus-classworlds-2.5.2.jar org.codehaus.classworlds.Launcher -Didea.version2019.1.1 -s %mavenHome%\conf\settings.xml
call %mvnCall% clean
call %mvnCall% package
pause

