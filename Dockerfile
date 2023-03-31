FROM tomcat:9-jre11-openjdk
COPY target/ui.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]
