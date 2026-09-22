package org.example.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.AfterEach;

public class FuncionApiTest {

    private int idReservaCreada;

    @Test
    public void deberiaObetenerListaDeFunciones() {
        given().baseUri("http://localhost:8080")
                .when().get("/funciones")
                .then().statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void deberiaCrearReservaExitosamente() {
        String body = "{ \"idUsuario\": 1, \"idAsiento\": 2, \"idFuncion\": 2 }";

        idReservaCreada = given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(body)
                .when()
                    .post("/reservas")
                .then()
                    .statusCode(200)
                    .body("usuario.idUsuario", equalTo(1))
                    .extract().path("idReserva");
    }

    @AfterEach
    public void limpiar() {
        given().baseUri("http://localhost:8080")
                .contentType("application/json")
                .when()
                    .delete("/reservas/" + idReservaCreada);
    }

    @Test
    public void deberiaRetornar409CuandoElAsientoYaEstaReservado() {
        String body = "{ \"idUsuario\": 1, \"idAsiento\": 2, \"idFuncion\": 2 }";

        idReservaCreada = given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(body)
                .when().post("/reservas")
                .then().extract().path("idReserva");

        given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(body)
                .when().post("/reservas")
                .then().statusCode(409);
    }

}
