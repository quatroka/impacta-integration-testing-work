package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import definition.ProcessEntity;
import groovy.json.internal.LazyMap;
import org.junit.Assert;
import support.RESTSupport;

import java.util.List;

public class CrudProcessSteps {

    @Given("^user wants save a process$")
    public void userWantsSaveAProcess() throws Throwable {
        ProcessEntity.clearFields();
    }

    @And("^and the user informs a process data$")
    public void andTheUserInformsAProcessData(DataTable table) {
        List<List<String>> columns = table.transpose().asLists(String.class);
        for (List<String> column : columns) {
            ProcessEntity.setField(column.get(0), column.get(1));
        }
    }

    @When("^user clicks on save button$")
    public void userClicksOnSaveButton() {
        RESTSupport.executePost(ProcessEntity.getEndPoint(), ProcessEntity.getFields());

    }

    @Then("^user should see \"([^\"]*)\" message$")
    public void userShouldSeeMessage(String message) {
        LazyMap messageJson = new LazyMap();
        messageJson.put("Consulta realizada com sucesso", 200);
        messageJson.put("Salvo com sucesso", 201);
        messageJson.put("Processo editado com sucesso", 200);
        messageJson.put("Deletado com sucesso", 204);
        messageJson.put("Erro ao salvar processo", 422);
        messageJson.put("Erro ao editar processo", 422);
        Assert.assertEquals(messageJson.get(message), RESTSupport.getResponseCode());
    }

    @When("^user clicks on see button$")
    public void userClicksOnSeeButton() {
        final String endpoint = ProcessEntity.getEndPoint() + "/" + ProcessEntity.getId() + ".json";
        RESTSupport.executeGet(endpoint);
    }

    @Given("^user wants see a process with ID$")
    public void userWantsSeeAProcessWithID() {
        ProcessEntity.setId(RESTSupport.key("id").toString());
    }

    @Given("^user wants edit a process$")
    public void userWantsEditAProcessWithID() {
        if (RESTSupport.key("id") != null) {
            ProcessEntity.setId(RESTSupport.key("id").toString());
        }
    }

    @And("^user informs \"([^\"]*)\" equals to \"([^\"]*)\"$")
    public void userInformsEqualsTo(String field, String value) {
        ProcessEntity.setField(field, value);
    }

    @When("^user clicks on edit button$")
    public void userClicksOnEditButton() {
        final String endpoint = ProcessEntity.getEndPoint() + "/" + ProcessEntity.getId() + ".json";
        RESTSupport.executePut(endpoint, ProcessEntity.getFields());
    }

    @Given("^user wants delete a process with ID$")
    public void userWantsDeleteAProcessWithID() {
        ProcessEntity.setId(RESTSupport.key("id").toString());
    }

    @When("^user clicks on delete button$")
    public void userClicksOnDeleteButton() {
        final String endpoint = ProcessEntity.getEndPoint() + "/" + ProcessEntity.getId() + ".json";
        RESTSupport.executeDelete(endpoint);
    }

}
