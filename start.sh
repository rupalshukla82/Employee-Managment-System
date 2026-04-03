#!/bin/sh

echo "Using PORT: $PORT"

# Replace default port 8080 with Railway dynamic port
sed -i "s/port=\"8080\"/port=\"${PORT}\"/" /usr/local/tomcat/conf/server.xml

# Start Tomcat
catalina.sh run
