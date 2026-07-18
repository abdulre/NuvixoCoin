#!/bin/bash
# NuvixoCoin — generate a genesis keypair
# Requires the node to be compiled first.
# The PUBLIC key goes into conf/data/genesisParameters.json
# Keep the SECRET PHRASE offline and never share it.
echo "To generate a genesis keypair, use the NuvixoCoin passphrase tool:"
echo ""
echo "  java -cp nuvixocoin.jar nxt.crypto.Crypto"
echo ""
echo "Or use the web UI on a local testnet node:"
echo "  1. Start NuvixoCoin with nxt.isTestnet=true"
echo "  2. Go to http://localhost:7876"
echo "  3. Create a new account — copy the public key"
echo "  4. Paste the public key into conf/data/genesisParameters.json"
