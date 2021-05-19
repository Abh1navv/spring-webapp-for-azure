package com.springpractice.azure.helpers;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

public class KeyVaultHelper {
    private static KeyVaultHelper keyVaultHelper;
    private final SecretClient secretClient;
    private KeyVaultHelper() {
        String keyVaultUri = System.getenv("KEY_VAULT_URI");
        secretClient = new SecretClientBuilder()
                .vaultUrl(keyVaultUri)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }

    public String getKeyVaultSecret(String secretName) {
        KeyVaultSecret retrievedSecret = secretClient.getSecret(secretName);
        return retrievedSecret.getValue();
    }

    public static KeyVaultHelper getInstance() {
        if(null == keyVaultHelper) {
            keyVaultHelper = new KeyVaultHelper();
        }
        return keyVaultHelper;
    }
}
