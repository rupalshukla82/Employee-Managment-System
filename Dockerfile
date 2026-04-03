FROM tomcat:10-jdk17

RUN rm -rf /usr/local/tomcat/webapps/*

COPY dist/employee.war /usr/local/tomcat/webapps/ROOT.war

# Copy startup script
COPY start.sh /start.sh
RUN chmod +x /start.sh

EXPOSE 8080

CMD ["/start.sh"]
