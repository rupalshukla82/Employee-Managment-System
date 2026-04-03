#!/bin/sh

PORT=${PORT:-8080}

echo "Using PORT: $PORT"

sed -i "s/port=\"8080\"/port=\"${PORT}\"/" /usr/local/tomcat/conf/server.xml

catalina.sh run
