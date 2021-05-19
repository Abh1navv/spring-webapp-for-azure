package com.springpractice.azure.helpers;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.specialized.BlobOutputStream;
import com.azure.storage.blob.specialized.BlockBlobClient;

public class BlobHelper {

    private static BlobHelper blobHelper;
    private final BlobServiceClient blobServiceClient;
    private BlobHelper() {
        String connectionString = KeyVaultHelper.getInstance().getKeyVaultSecret("datablobsas");
        blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
    }

    public String getStaticResourceURI() {
        return System.getenv("STATIC_BLOB_URI");
    }

    public String getJSONData(String jsonName) {
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient("data");
        BlockBlobClient blobClient = blobContainerClient.getBlobClient(jsonName + ".json").getBlockBlobClient();
        return blobClient.openInputStream().toString();
    }

    public static BlobHelper getInstance() {
        if(null == blobHelper) {
            blobHelper = new BlobHelper();
        }
        return blobHelper;
    }
}
