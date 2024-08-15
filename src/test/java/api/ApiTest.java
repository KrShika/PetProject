package api;

import com.github.javafaker.Faker;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashWiseToken;
import utilities.Config;

import java.util.HashMap;
import java.util.List;


public class ApiTest {

    @Test
    public void GetSingleSeller() {
        String url = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/sellers/" + 4668;
        String token = CashWiseToken.GetToken();

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .get(url);


        String expectedEmail = response.jsonPath().getString("email");
        Assert.assertFalse(expectedEmail.isEmpty());
        Assert.assertTrue(expectedEmail.endsWith(".com"));

    }

    @Test
    public void GetAllSellers() {
        String url = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/sellers";
        String token = CashWiseToken.GetToken();

        HashMap<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 4);

        Response response = RestAssured.given().auth()
                .oauth2(token).params(params)
                .get(url);

        int statusCode = response.statusCode();
        Assert.assertEquals(200, statusCode);
        response.prettyPrint();

        String email1 = response.jsonPath().getString("responses[0].email");
        Assert.assertFalse(email1.isEmpty());

        String email2 = response.jsonPath().getString("responses[1].email");
        Assert.assertFalse(email2.isEmpty());

        String email4 = response.jsonPath().getString("responses[3].email");
        Assert.assertFalse(email4.isEmpty());


    }

    @Test
    public void GetAllSellersLoop() {

        String endpoint = "https://backend.cashwise.us/api/myaccount/sellers";
        String token = CashWiseToken.GetToken();

        HashMap<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 4);

        Response response = RestAssured.given().auth().oauth2(token)
                .params(params).get(endpoint);
        response.prettyPrint();

        int statusCode = response.statusCode();
        Assert.assertEquals(200, statusCode);

        String company_name = response.jsonPath().getString("responses[0].company_name");
        Assert.assertFalse(company_name.isEmpty());

        List<String> listOfEmails = response.jsonPath().getList("responses.email");

        for (String emails: listOfEmails){
            Assert.assertFalse(emails.isEmpty());
        }


    }






}