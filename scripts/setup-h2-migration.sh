#!/bin/bash
# NuvixoCoin — H2 1.x → 2.x SQL migration helper
# Run this to check your NxtDbVersion.java for incompatible H2 1.x SQL patterns
echo "Scanning NxtDbVersion.java for H2 1.x incompatible patterns..."
echo ""

FILE="src/java/nxt/NxtDbVersion.java"

echo "=== IDENTITY columns (must become BIGINT GENERATED ALWAYS AS IDENTITY) ==="
grep -n " IDENTITY\b" "$FILE" | head -20

echo ""
echo "=== MVCC=TRUE in connection URL (remove this — H2 2.x always uses MVCC) ==="
grep -rn "MVCC=TRUE\|MVCC=true" src/ conf/ | head -10

echo ""
echo "=== VALUE keyword (reserved in H2 2.x — add NON_KEYWORDS=VALUE to URL) ==="
grep -n "\bVALUE\b" "$FILE" | head -10

echo ""
echo "Fix summary:"
echo "  1. Replace 'db_id IDENTITY' with 'db_id BIGINT GENERATED ALWAYS AS IDENTITY'"
echo "  2. Remove 'MVCC=TRUE' from all JDBC URLs in nxt-default.properties"
echo "  3. Add 'NON_KEYWORDS=VALUE' to nxt.dbParams in nxt-default.properties"
