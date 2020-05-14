package <%= packageName %>.controller;

import <%= packageName %>.entity.<%= entityName %>;
import <%= packageName %>.service.<%= entityName %>Service;
import <%= packageName %>.exception.ExceptionResponse;
import <%= packageName %>.model.<%= entityName %>Response;
import <%= packageName %>.model.<%= entityName %>Request;
import <%= packageName %>.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("<%= basePath %>")
@Slf4j
@Api(value = "<%= entityName %>Controller", produces = "application/json", tags = { "Controlador <%= entityName %>" })
public class <%= entityName %>Controller {

    private final <%= entityName %>Service <%= entityVarName %>Service;

    @Autowired
    public <%= entityName %>Controller(<%= entityName %>Service <%= entityVarName %>Service) {
        this.<%= entityVarName %>Service = <%= entityVarName %>Service;
    }

    @GetMapping
    public List<<%= entityName %>> getAll<%= entityName %>s() {
        return <%= entityVarName %>Service.findAll<%= entityName %>s();
    }

    @ApiOperation(value = "Obtiene <%= entityName %> por ID", tags = { "Controlador <%= entityName %>" })
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "<%= entityName %> encontrada", response = <%= entityName %>.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<<%= entityName %>> get<%= entityName %>ById(@PathVariable Long id) {
        return <%= entityVarName %>Service.find<%= entityName %>ById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Registra <%= entityName %>", tags = { "Controlador <%= entityName %>" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "<%= entityName %> registrada", response = <%= entityName %>Request.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<<%= entityName %>Response> create<%= entityName %>(@RequestBody @Validated <%= entityName %>Request <%= entityVarName %>Request) {
        <%= entityVarName %>Service.save<%= entityName %>(<%= entityVarName %>Request);
        return new ResponseEntity<>(new <%= entityName %>Response(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza <%= entityName %>", tags = { "Controlador <%= entityName %>" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "<%= entityName %> actualizada", response = <%= entityName %>Request.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<<%= entityName %>Response> update<%= entityName %>(@PathVariable Long id, @RequestBody <%= entityName %>Request <%= entityVarName %>Request) {

        return <%= entityVarName %>Service.find<%= entityName %>ById(id)
                .map(<%= entityVarName %>RequestObj -> {
                    <%= entityVarName %>Request.setId(id);
                    <%= entityVarName %>Service.update<%= entityName %>(<%= entityVarName %>Request);
                    return new ResponseEntity<>(new <%= entityName %>Response(Constant.REG_ACT_ACCEPTED), HttpStatus.CREATED);
                })
                .orElseGet(() -> new ResponseEntity<>(new <%= entityName %>Response(Constant.REG_ACT_NOT_ACCEPTED), HttpStatus.CREATED));
    }

    @ApiOperation(value = "Elimina <%= entityName %>", tags = { "Controlador <%= entityName %>" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "<%= entityName %> eliminada", response = <%= entityName %>Request.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<<%= entityName %>Response> delete<%= entityName %>(@PathVariable Long id) {

        return <%= entityVarName %>Service.find<%= entityName %>ById(id)
                .map(<%= entityVarName %> -> {
                    <%= entityVarName %>Service.delete<%= entityName %>ById(id);
                    return new ResponseEntity<>(new <%= entityName %>Response(Constant.REG_ELI_OK), HttpStatus.CREATED);
                })
                .orElseGet(() -> new ResponseEntity<>(new <%= entityName %>Response(Constant.REG_ELI_NOT_OK), HttpStatus.CREATED));
    }
}
