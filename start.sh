#!/bin/sh

echo "Railway PORT is: $PORT"

# DO NOT fallback to 8080
# If PORT is empty, fail immediately
if [ -z "$PORT" ]; then
  echo "ERROR: PORT not set by Railway"
  exit 1
fi

# Replace port in Tomcat config
sed -i "s/port=\"8080\"/port=\"${PORT}\"/" /usr/local/tomcat/conf/server.xml

# Start Tomcat
catalina.sh run
