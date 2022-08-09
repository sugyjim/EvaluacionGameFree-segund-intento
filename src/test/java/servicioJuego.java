import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;



import com.google.gson.JsonObject;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.*;
import net.thucydides.core.annotations.Step;
import org.hamcrest.CoreMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Math.log;
import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class servicioJuego {
        srvicioCoreJ srvicioCoreJ;

        private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(servicioJuego.class);
        static private final String BASE_URL = "https://www.freetogame.com/api/games";

        private static RequestSpecification requestSpec;
        private  ResponseSpecification responseSpec;

        private Response response;
        private RequestSpecBuilder builder;
        private RequestSpecification requestSpecification;
        private String bodyPost;

        @Before
        public void init() {

                LOGGER.info(" Inicializa el constructor request ");
                requestSpec = new RequestSpecBuilder()
                        .setBaseUri(BASE_URL)
                        .build();

                LOGGER.info(" Inicializa el constructor response ");
                responseSpec = new ResponseSpecBuilder()
                        .expectStatusCode(200)
                        .expectContentType(ContentType.JSON)
                        .build();
        }
        public void validateStatusCode(int i) {
                assertThat(lastResponse().statusCode(), is(i));
        }

        @Then("el codigo de respuesta es {int}")
        public  void elCodigoDeRespuestaEs(int codigoRespuestaEsperado) {
                LOGGER.info("i elCodigoDeRespuestaEs");
                srvicioCoreJ.validarCodigoEsperado(codigoRespuestaEsperado);
                String titulo = lastResponse().jsonPath().param("id", 521).getString("title");
                LOGGER.info("f elCodigoDeRespuestaEs");
        }


        @Given("la URL_BASE del Servicio es {string}")
        public void laURL_BASEDelServicioEs(String urlBase) {
                LOGGER.info("i laURL_BASEDelServicioEs");
                LOGGER.info("URL BASE = " + urlBase);
                srvicioCoreJ.configurarURLBaseServicio(urlBase);
                LOGGER.info("f laURL_BASEDelServicioEs");
        }



        @Given("la URL_BASE del Servicio responde {int}")
        public void laURL_BASEDelServicioResponde(int codigoRespuestaEsperado) {
                LOGGER.info("i laURL_BASEDelServicioResponde");
                LOGGER.info("Consulta la Disponibilidad del Servicio ... ");
                srvicioCoreJ.consultarServicioDisponibilidad(null);
                srvicioCoreJ.validarCodigoEsperado(codigoRespuestaEsperado);
                LOGGER.info("f laURL_BASEDelServicioResponde");
        }

        @When("consulto el Servicio con el Path {string}")
        public void consultoElServicio(String path) {
                LOGGER.info("i consultoElServicioConElPath");
              // String titulo = lastResponse().jsonPath().param("id", 521).getString("title");
               srvicioCoreJ.consultarServicioGet(null,"tytle/id");
                LOGGER.info("f consultoElServicioConElPath");
        }

        @And("valido el resultado")
        public void validoElResultado() {
                srvicioCoreJ.validaRespuesta();
        }
        public void consultarServicioGet(String url, String path){

                LOGGER.info("i consultarServicioPostGet");
                response = null;

                url =BASE_URL;
                //jsonBody = analizaJsonBody(jsonBody);

               // URL = concatenarURL(url,path);
                LOGGER.info("concatenarURL: "+ BASE_URL);

                //Se inicializa la URL base
                requestSpec = new RequestSpecBuilder().setBaseUri(BASE_URL).build();

              // resetRequestResponseCapture();

                RequestSpecification reqGiven = given();

                reqGiven.spec(requestSpec);
                reqGiven.contentType(ContentType.JSON);
                response = reqGiven
                        .log().all()
                       // .filter(new RequestLoggingFilter(requestCapture))
                     //   .filter(new ResponseLoggingFilter(responseCapture))
                        .pathParams("id", "521")
                        .pathParams("tytle", "")
                        .queryParam("platform", "pc")
                        .queryParam("category", "chooser")
                        //  .queryParam("Account_No", "1")
                        //.body(jsonBody.toString())
                        .when()
                        .get();
                response.toString();
                LOGGER.info("Response StatusCode: " + response.getStatusCode());
                LOGGER.info("Response Body: " + response.getBody().asString());//¿PUEDO PONER UN JSON?

             //   sRequest=requestWriter.toString();
              //  sResponse=responseWriter.toString();

                LOGGER.info("sRequest Completo: ");
             //   LOGGER.info(sRequest);
                LOGGER.info("sResponse Completo: ");
              //  LOGGER.info(sResponse);
                LOGGER.info("f consultarServicioGet");
        }


}


