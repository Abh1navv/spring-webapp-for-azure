package com.springpractice.azure.helpers;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.specialized.BlobOutputStream;
import com.azure.storage.blob.specialized.BlockBlobClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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

    public String getJSONData(String jsonName) throws IOException {
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient("data");
        BlockBlobClient blobClient = blobContainerClient.getBlobClient(jsonName + ".json").getBlockBlobClient();
        return getResponse(blobClient.openInputStream());
    }

    public static BlobHelper getInstance() {
        if(null == blobHelper) {
            blobHelper = new BlobHelper();
        }
        return blobHelper;
    }

    /**
     * Convert JSON Response to String
     *
     * @param inputStream@return
     *            converted String
     * @throws IOException
     *             IO exception while reading input stream
     */
    private static String getResponse(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        return sb.toString();
    }
}
