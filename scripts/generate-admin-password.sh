#!/bin/bash
# NuvixoCoin — generate a strong admin password
# Run this and paste the output into conf/nxt.properties as:
#   nxt.adminPassword=<generated>
echo "Your NuvixoCoin admin password:"
openssl rand -base64 32
echo ""
echo "Paste it into conf/nxt.properties:"
echo "  nxt.adminPassword=<value above>"
echo ""
echo "NEVER commit nxt.properties to version control."
