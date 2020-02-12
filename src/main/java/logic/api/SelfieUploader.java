package logic.api;

import io.restassured.RestAssured;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public interface SelfieUploader extends Specification {
    default String getApplicationID(String fwdBasket) {
        return RestAssured
                .given()
                .spec(getRequestSpecification())
                .basePath("contractName" + fwdBasket)
                .get()
                .then()
                .spec(getResponseSpecification()).extract().body()
                .jsonPath()
                .getString("contractName")
                ;
    }

    default void selfieUploadApi(String fwdBasket, String authToken) {
        String applicationID = getApplicationID(fwdBasket);
        RestAssured
                .given()
                .spec(getRequestSpecification())
                .header("authorization", "Bearer " + authToken)
                .header("device-type", "WEB")
                .basePath("contractName" + applicationID + "/photo")
                .body(new HashMap<String, Object>() {{
                    put("imageType", "SELFIE");
                    try {
                        put("imageData", new String(Base64.getEncoder().encode(FileUtils.readFileToByteArray(new File("src/main/resources/pass.jpeg")))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }})
                .post()
                .then()
                .spec(getResponseSpecification())
        ;
        System.out.println("wait until selfie uploaded");
        for (int i = 0; i < 10; i++) {
            String status = RestAssured
                    .given()
                    .spec(getRequestSpecification())
                    .basePath("contractName" + applicationID + "/contractName")
                    .get()
                    .then()
                    .extract().body().jsonPath().getString("resultData.status");
            if (status.equals("CHECKED_SUCCESS")) return;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException();
    }
}
