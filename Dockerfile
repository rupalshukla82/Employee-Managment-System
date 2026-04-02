FROM tomcat:10-jdk17

# Remove default apps from Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your Ant-built WAR file to Tomcat
COPY dist/employee.war /usr/local/tomcat/webapps/ROOT.war

# Expose Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]