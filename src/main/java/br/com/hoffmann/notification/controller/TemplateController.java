package br.com.hoffmann.notification.controller;

import br.com.hoffmann.notification.domain.enums.TemplateType;
import br.com.hoffmann.notification.domain.request.TemplateRequest;
import br.com.hoffmann.notification.domain.response.TemplateResponse;
import br.com.hoffmann.notification.service.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(value = "Templates - endPoints API", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@Validated
@RequestMapping(path = "/v1/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @ApiOperation(value = "EndPoint para cadastro de Templates")
    @RequestMapping(value = "/cadastraTemplate", method = RequestMethod.POST)
    @Validated(TemplateRequest.createRequest.class)
    public ResponseEntity<TemplateResponse> cadastraFornecedor(
            @RequestBody @Valid TemplateRequest request) {
        templateService.createTemplate(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "EndPoint para exclusão de Templates")
    @RequestMapping(value = "/deletaTemplate/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TemplateResponse> deletaTemplate(
            @PathVariable(value = "id") @NotNull Long id) {
        templateService.deletaTemplate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "EndPoint para alteração de Templates")
    @RequestMapping(value = "/updateTemplate/{id}", method = RequestMethod.PUT)
    @Validated(TemplateRequest.updateRequest.class)
    public ResponseEntity<TemplateResponse> updateTemplate(
            @PathVariable(value = "id") @NotNull Long id,
            @RequestBody @Valid TemplateRequest request) throws NotFoundException {
        templateService.updateTemplate(request, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "EndPoint para fazer a busca de Templates")
    @RequestMapping(value = "/buscaTemplates", method = RequestMethod.GET)
    public ResponseEntity<List<TemplateResponse>> buscaTemplates() {
        List<TemplateResponse> response = templateService.buscaTemplates();
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "EndPoint para fazer a busca de um template pelo nome")
    @RequestMapping(value = "/buscaTemplateByName/{templateName}/{templateType}", method = RequestMethod.GET)
    public ResponseEntity<TemplateResponse> buscaFornecedorPorCNPJ(@PathVariable(value = "templateName") @NotNull String templateName,
                                                                   @PathVariable(value = "templateType") @NotNull TemplateType templateType) throws NotFoundException {
        TemplateResponse response = templateService.TemplateByName(templateName, templateType);
        return ResponseEntity.ok().body(response);
    }
}
