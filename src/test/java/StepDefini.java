import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class StepDefini {
    servicioJuego servicioJuego;
    static private final String BASE_URL = "https://www.freetogame.com/api/games";



    @Given("valido que el servicio responde {int}")
    public void validoQueElServicioResponde(int arg0) {
        servicioJuego.elCodigoDeRespuestaEs(200);
    }

    @When("entro a la pagina")
    public void entroALaPagina() {
    }

    @Then("Accedo a la lista de juegos")
    public void accedoALaListaDeJuegos( ){
        servicioJuego.consultoElServicio("https://www.freetogame.com/api/games");
    }

    @Given("que tengo una consulta fallida")
    public void queTengoUnaConsultaFallida() {
        servicioJuego.consultoElServicio("id/tytle");
    }

    @When("se ingresa un id")
    public void seIngresaUnId() {
        servicioJuego.consultoElServicio("https://www.freetogame.com/api/games");
    }

    @Then("muestra un codigo de error {int}")
    public void muestraUnCodigoDeError(int arg0) {
        servicioJuego.validateStatusCode(404);
    }
}