#!/bin/sh

echo "Railway PORT is: $PORT"

# Fail fast if PORT is not set
if [ -z "$PORT" ]; then
  echo "ERROR: PORT environment variable is not set"
  exit 1
fi

SERVER_XML="/usr/local/tomcat/conf/server.xml"

echo "Configuring Tomcat HTTP Connector to listen on port ${PORT}..."

# Use sed to replace port="8080" on the HTTP Connector line.
# The pattern anchors on the Connector element and only rewrites the
# port="8080" attribute, leaving the shutdown port (8005) and any AJP
# connector untouched.
sed -i 's/\(<Connector[^>]*\)port="8080"/\1port="'"${PORT}"'"/' "${SERVER_XML}"

# Verify the change was applied
echo "Resulting Connector lines in server.xml after substitution:"
grep "Connector" "${SERVER_XML}"

# Export port so any child process can see it
export PORT

echo "Starting Tomcat..."
exec catalina.sh run

