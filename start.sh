#!/bin/sh

echo "Railway PORT is: $PORT"

# Fail fast if PORT is not set
if [ -z "$PORT" ]; then
  echo "ERROR: PORT environment variable is not set"
  exit 1
fi

SERVER_XML="/usr/local/tomcat/conf/server.xml"

echo "Configuring Tomcat HTTP Connector to listen on port ${PORT}..."

# Use Python to reliably update only the HTTP Connector port in server.xml.
# This avoids sed quoting issues and won't accidentally change the shutdown
# port (8005) or the AJP connector port.
python3 - <<EOF
import xml.etree.ElementTree as ET

tree = ET.parse("${SERVER_XML}")
root = tree.getroot()

updated = False
for connector in root.iter("Connector"):
    protocol = connector.get("protocol", "")
    port_val = connector.get("port", "")
    # Target the HTTP/1.1 connector (or the default one on port 8080)
    if "HTTP" in protocol or port_val == "8080":
        print(f"  Updating Connector (protocol={protocol!r}, port={port_val!r}) -> port=${PORT}")
        connector.set("port", "${PORT}")
        updated = True
        break  # only update the first HTTP connector

if not updated:
    print("  WARNING: No HTTP Connector found; falling back to sed")

tree.write("${SERVER_XML}", xml_declaration=True, encoding="UTF-8")
EOF

# Verify the change was applied
echo "Resulting Connector port lines in server.xml:"
grep -i "Connector" "${SERVER_XML}" | grep -i "port"

# Export port so any child process can see it
export PORT

echo "Starting Tomcat..."
exec catalina.sh run

