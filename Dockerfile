FROM tomcat:10-jdk17-temurin

# Clean default webapps
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Deploy WAR as ROOT
COPY dist/employee.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
