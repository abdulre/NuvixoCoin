#!/bin/bash
# NuvixoCoin — Migration Verification Script
# Confirms the javax→jakarta, H2 1.x→2.x, and other dependency migrations
# were applied correctly. Run this before every build.gradle compile attempt.

echo "=== javax.servlet check (should be 0) ==="
grep -rl "javax\.servlet" src/ addons/ 2>/dev/null | wc -l

echo "=== bare db_id IDENTITY check (should be 0) ==="
grep -c "db_id IDENTITY[,)]" src/java/nxt/NxtDbVersion.java 2>/dev/null || echo 0

echo "=== bare ARRAY check (should be 0) ==="
grep -oP '(?<!VARCHAR )(?<!VARBINARY )(?<!BIGINT )\bARRAY\b' src/java/nxt/NxtDbVersion.java 2>/dev/null | wc -l

echo "=== bare SslContextFactory() check (should be 0) ==="
grep -rn "new SslContextFactory()" src/ 2>/dev/null | wc -l

echo "=== NON_KEYWORDS=VALUE present in dbParams (should be 2: main + testnet) ==="
grep -c "NON_KEYWORDS=VALUE" conf/nxt-default.properties

echo ""
echo "If all values above are 0 (except the last, which should be 2), the migration is intact."
