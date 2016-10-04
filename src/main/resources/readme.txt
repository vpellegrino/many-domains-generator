How to compile:
- Go to the project root (where you can find pom.xml)
- You can modify log verbosity, editing src/main/resources/log4j.properties
- Run: mvn clean install
- As result, if there will not be errors, you will have two jar: ManyDomainsGenerator.jar (the tool) e ManyDomainsGenerator-javadoc.jar (the documentation)



**********************************************************************************************************************************************
How to execute:
- Go to the folder where you have the jar tool.
- Run: java -jar ManyDomainsGenerator.jar [arguments]
- Arguments list is:
	-d (--domains)	N	: domains number
	-l (--levels)	N	: levels number
- You can check the execution by reading your STDOUT. The process result is a .csv file, containing generated entities