package com.springpractice.azure.connections;
import com.azure.core.util.polling.SyncPoller;
import com.azure.identity.DefaultAzureCredentialBuilder;

import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.DeletedSecret;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

public class KeyVaultHelper {
    private KeyVaultHelper() {};
    private static final SecretClient secretClient;
    static {
        String keyVaultUri = "KEY_VAULT_URI";
        secretClient = new SecretClientBuilder()
                .vaultUrl(keyVaultUri)
                .credential(DefaultAzureCredentialBuilder.build())
                .buildClient();
    }
    public static String getKeyVaultSecret() {
            return null;
    }
}
