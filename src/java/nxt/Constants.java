/*
 * NuvixoCoin — forked from NXT Core / Jelurida NXT 1.13.1
 * Original: Copyright © 2013-2016 The Nxt Core Developers.
 *           Copyright © 2016-2023 Jelurida IP B.V.
 *           Copyright © 2023-2025 Jelurida Swiss SA
 * NuvixoCoin modifications © 2025 NuvixoCoin Developers
 */

package nxt;

import java.math.BigInteger;

public final class Constants {

    // ── Runtime flags (set via nxt.properties, never edit here) ──────────────
    public static final boolean isTestnet        = Nxt.getBooleanProperty("nxt.isTestnet");
    public static final boolean isOffline        = Nxt.getBooleanProperty("nxt.isOffline");
    public static final boolean isLightClient    = Nxt.getBooleanProperty("nxt.isLightClient");
    public static final boolean isAutomatedTest  = isTestnet && Nxt.getBooleanProperty("nxt.isAutomatedTest");
    public static final String  customLoginWarning = Nxt.getStringProperty("nxt.customLoginWarning", null, false, "UTF-8");

    // ── Chain Identity ────────────────────────────────────────────────────────
    public static final String COIN_SYMBOL    = "NXC";
    public static final String ACCOUNT_PREFIX = "NXC";
    public static final String PROJECT_NAME   = "NuvixoCoin";

    // ── Supply & Precision ────────────────────────────────────────────────────
    public static final long MAX_BALANCE_NXT = 1_000_000_000L;   // 1 billion NXC
    public static final long ONE_NXT         = 100_000_000L;     // 8 decimal places
    public static final long MAX_BALANCE_NQT = MAX_BALANCE_NXT * ONE_NXT;

    // ── Block Timing & Forging ────────────────────────────────────────────────
    public static final int  BLOCK_TIME = 60;
    public static final long INITIAL_BASE_TARGET =
            BigInteger.valueOf(2).pow(63)
                      .divide(BigInteger.valueOf((long) BLOCK_TIME * MAX_BALANCE_NXT))
                      .longValue();
    public static final long MAX_BASE_TARGET  = INITIAL_BASE_TARGET * (isTestnet ? MAX_BALANCE_NXT : 50);
    public static final long MIN_BASE_TARGET  = INITIAL_BASE_TARGET * 9 / 10;
    public static final int  MIN_BLOCKTIME_LIMIT = BLOCK_TIME - 7;
    public static final int  MAX_BLOCKTIME_LIMIT = BLOCK_TIME + 7;
    public static final int  BASE_TARGET_GAMMA   = 64;
    public static final int  MAX_ROLLBACK = Math.max(Nxt.getIntProperty("nxt.maxRollback"), 720);
    public static final int  GUARANTEED_BALANCE_CONFIRMATIONS =
            isTestnet ? Nxt.getIntProperty("nxt.testnetGuaranteedBalanceConfirmations", 1440) : 1440;
    public static final int  LEASING_DELAY =
            isTestnet ? Nxt.getIntProperty("nxt.testnetLeasingDelay", 1440) : 1440;
    public static final long MIN_FORGING_BALANCE_NQT = 1_000L * ONE_NXT;
    public static final int  MAX_TIMEDRIFT   = 15;
    public static final int  FORGING_DELAY   = Nxt.getIntProperty("nxt.forgingDelay");
    public static final int  FORGING_SPEEDUP = Nxt.getIntProperty("nxt.forgingSpeedup");
    public static final int  BATCH_COMMIT_SIZE = Nxt.getIntProperty("nxt.batchCommitSize", Integer.MAX_VALUE);

    // ── Transaction Limits ────────────────────────────────────────────────────
    public static final int MAX_NUMBER_OF_TRANSACTIONS =
            Nxt.getIntProperty("nxt.maxNumberOfTransactions", 255);
    public static final int MIN_TRANSACTION_SIZE = 176;
    public static final int MAX_PAYLOAD_LENGTH   = MAX_NUMBER_OF_TRANSACTIONS * MIN_TRANSACTION_SIZE;

    // ── Phasing ───────────────────────────────────────────────────────────────
    public static final byte MAX_PHASING_VOTE_TRANSACTIONS   = 10;
    public static final byte MAX_PHASING_WHITELIST_SIZE      = 10;
    public static final byte MAX_PHASING_LINKED_TRANSACTIONS = 10;
    public static final int  MAX_PHASING_DURATION            = 14 * 1440;
    public static final int  MAX_PHASING_REVEALED_SECRET_LENGTH = 100;

    // ── Messaging & Aliases ───────────────────────────────────────────────────
    public static final int MAX_ALIAS_URI_LENGTH   = 1000;
    public static final int MAX_ALIAS_LENGTH       = 100;
    public static final int MAX_ARBITRARY_MESSAGE_LENGTH         = 160;
    public static final int MAX_ENCRYPTED_MESSAGE_LENGTH         = 160 + 16;
    public static final int MAX_PRUNABLE_MESSAGE_LENGTH          = 42 * 1024;
    public static final int MAX_PRUNABLE_ENCRYPTED_MESSAGE_LENGTH = 42 * 1024;
    public static final int MIN_PRUNABLE_LIFETIME =
            isTestnet ? 1440 * 60 : 14 * 1440 * 60;
    public static final int  MAX_PRUNABLE_LIFETIME;
    public static final boolean ENABLE_PRUNING;
    static {
        int maxPrunableLifetime = Nxt.getIntProperty("nxt.maxPrunableLifetime");
        ENABLE_PRUNING        = maxPrunableLifetime >= 0;
        MAX_PRUNABLE_LIFETIME = ENABLE_PRUNING
                ? Math.max(maxPrunableLifetime, MIN_PRUNABLE_LIFETIME)
                : Integer.MAX_VALUE;
    }
    public static final boolean INCLUDE_EXPIRED_PRUNABLE = Nxt.getBooleanProperty("nxt.includeExpiredPrunable");

    // ── Account ───────────────────────────────────────────────────────────────
    public static final int MAX_ACCOUNT_NAME_LENGTH        = 100;
    public static final int MAX_ACCOUNT_DESCRIPTION_LENGTH = 1000;
    public static final int MAX_ACCOUNT_PROPERTY_NAME_LENGTH  = 32;
    public static final int MAX_ACCOUNT_PROPERTY_VALUE_LENGTH = 160;

    // ── Assets ────────────────────────────────────────────────────────────────
    public static final int  MAX_ASSET_PROPERTY_NAME_LENGTH         = 32;
    public static final int  MAX_ASSET_PROPERTY_VALUE_LENGTH        = 160;
    public static final long MAX_ASSET_QUANTITY_QNT                 = 1_000_000_000L * 100_000_000L;
    public static final int  MIN_ASSET_NAME_LENGTH                  = 3;
    public static final int  MAX_ASSET_NAME_LENGTH                  = 10;
    public static final int  MAX_ASSET_DESCRIPTION_LENGTH           = 1000;
    public static final int  MAX_SINGLETON_ASSET_DESCRIPTION_LENGTH = 160;
    public static final int  MAX_ASSET_TRANSFER_COMMENT_LENGTH      = 1000;
    public static final int  MAX_DIVIDEND_PAYMENT_ROLLBACK          = 1441;

    // ── Polls ─────────────────────────────────────────────────────────────────
    public static final int  MAX_POLL_NAME_LENGTH        = 100;
    public static final int  MAX_POLL_DESCRIPTION_LENGTH = 1000;
    public static final int  MAX_POLL_OPTION_LENGTH      = 100;
    public static final int  MAX_POLL_OPTION_COUNT       = 100;
    public static final int  MAX_POLL_DURATION           = 14 * 1440;
    public static final byte MIN_VOTE_VALUE = -92;
    public static final byte MAX_VOTE_VALUE =  92;
    public static final byte NO_VOTE_VALUE  = Byte.MIN_VALUE;

    // ── Digital Goods Store ───────────────────────────────────────────────────
    public static final int MAX_DGS_LISTING_QUANTITY           = 1_000_000_000;
    public static final int MAX_DGS_LISTING_NAME_LENGTH        = 100;
    public static final int MAX_DGS_LISTING_DESCRIPTION_LENGTH = 1000;
    public static final int MAX_DGS_LISTING_TAGS_LENGTH        = 100;
    public static final int MAX_DGS_GOODS_LENGTH               = 1000;

    // ── Monetary System ───────────────────────────────────────────────────────
    public static final int  MIN_CURRENCY_NAME_LENGTH        = 3;
    public static final int  MAX_CURRENCY_NAME_LENGTH        = 10;
    public static final int  MIN_CURRENCY_CODE_LENGTH        = 3;
    public static final int  MAX_CURRENCY_CODE_LENGTH        = 5;
    public static final int  MAX_CURRENCY_DESCRIPTION_LENGTH = 1000;
    public static final long MAX_CURRENCY_TOTAL_SUPPLY       = 1_000_000_000L * 100_000_000L;
    public static final int  MAX_MINTING_RATIO               = 10000;

    // ── Shuffling ─────────────────────────────────────────────────────────────
    public static final byte  MIN_NUMBER_OF_SHUFFLING_PARTICIPANTS = 3;
    public static final byte  MAX_NUMBER_OF_SHUFFLING_PARTICIPANTS = 30;
    public static final short MAX_SHUFFLING_REGISTRATION_PERIOD   = (short)(1440 * 7);
    public static final short SHUFFLING_PROCESSING_DEADLINE       = (short)(isTestnet ? 10 : 100);

    // ── Tagged Data ───────────────────────────────────────────────────────────
    public static final int MAX_TAGGED_DATA_NAME_LENGTH        = 100;
    public static final int MAX_TAGGED_DATA_DESCRIPTION_LENGTH = 1000;
    public static final int MAX_TAGGED_DATA_TAGS_LENGTH        = 100;
    public static final int MAX_TAGGED_DATA_TYPE_LENGTH        = 100;
    public static final int MAX_TAGGED_DATA_CHANNEL_LENGTH     = 100;
    public static final int MAX_TAGGED_DATA_FILENAME_LENGTH    = 100;
    public static final int MAX_TAGGED_DATA_DATA_LENGTH        = 42 * 1024;

    // ── Chain Checkpoints ─────────────────────────────────────────────────────
    public static final int MAX_REFERENCED_TRANSACTION_TIMESPAN = 60 * 1440 * 60;
    // Integer.MAX_VALUE = no genesis checksum enforced (correct for a fresh chain)
    // After launch, set to the actual block height of your first checkpoint.
    public static final int CHECKSUM_BLOCK_1    = Integer.MAX_VALUE;
    public static final int LAST_CHECKSUM_BLOCK = 0;
    // Keep at 0 until your chain has enough history; must also match html/www/js/nrs.constants.js
    public static final int LAST_KNOWN_BLOCK    = isTestnet ? 0 : 0;

    // ── Network Compatibility ─────────────────────────────────────────────────
    // {2,0} rejects all original NXT / NxtClone nodes — your chain only
    public static final int[] MIN_VERSION       = new int[]{2, 0};
    public static final int[] MIN_PROXY_VERSION = new int[]{2, 0};

    // ── Pool Deposits ─────────────────────────────────────────────────────────
    static final long UNCONFIRMED_POOL_DEPOSIT_NQT = (isTestnet ? 50L : 100L) * ONE_NXT;
    public static final long SHUFFLING_DEPOSIT_NQT = (isTestnet ? 7L : 1_000L) * ONE_NXT;

    // ── Misc ──────────────────────────────────────────────────────────────────
    public static final boolean correctInvalidFees = Nxt.getBooleanProperty("nxt.correctInvalidFees");
    public static final String  ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";
    public static final String  ALLOWED_CURRENCY_CODE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final boolean DISABLE_FULL_TEXT_SEARCH  = Nxt.getBooleanProperty("nxt.disableFullTextSearch");
    public static final boolean DISABLE_METADATA_DETECTION = Nxt.getBooleanProperty("nxt.disableMetadataDetection");

    private Constants() {}
}
