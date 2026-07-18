#!/bin/bash
# NuvixoCoin — Jetty 9→11 migration helper
# Jetty 11 uses jakarta.servlet instead of javax.servlet
# Run this ONCE after switching to build.gradle before compiling
echo "Migrating javax.servlet → jakarta.servlet..."
find src/java -name "*.java" -exec sed -i \
  's/import javax\.servlet\./import jakarta.servlet./g' {} +
echo "Done. Check git diff before committing."
