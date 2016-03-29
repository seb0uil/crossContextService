## crossContext
This repository show how to use cross context in a servlet container.

For this, there is 3 projects :

  - crossContextService : This project make a war you may place in service classloader (in Tomcat, this is $CATALINA_HOME/lib by default) 
  - crossContextImpl : This is the webapp where we implement the TestService
  - crossContextClient : This is the webapp where we call the TestService implementation
 
So in order this to work, you may have to generate all this stuff :
_mvn clean package_


