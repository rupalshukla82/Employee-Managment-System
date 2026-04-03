FROM tomcat:10-jdk17

RUN rm -rf /usr/local/tomcat/webapps/*

COPY dist/employee.war /usr/local/tomcat/webapps/ROOT.war

COPY start.sh /start.sh
RUN chmod +x /start.sh

# Default (Railway will override this)
ENV PORT=8080

EXPOSE 8080

CMD ["/start.sh"]
