package com.unir.ms_inventory_orders.controller;

import com.unir.ms_inventory_orders.model.Book;
import com.unir.ms_inventory_orders.model.db.Order;
import com.unir.ms_inventory_orders.model.request.OrderRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unir.ms_inventory_orders.services.OrdersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Orders Controller", description = "Microservicio encargado de ejecutar las acciones principales de la aplicación libros (compras, consultas de compras).")
public class OrdersController {
    private final OrdersService service;

    @GetMapping("/orders")
    @Operation(
		operationId = "Obtener ordenes de compra libros",
		description = "Operacion de lectura",
		summary = "Se devuelve una lista de todos las ordenes de compra libros almacenados en la base de datos.")
    @ApiResponse(
        responseCode = "200",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    public ResponseEntity<List<Order>> getOrders() {

        List<Order> orders = service.getOrders();
        if (orders != null) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/orders/{id}")
    @Operation(
        operationId = "Obtener una orden de compra libro",
        description = "Operacion de lectura",
        summary = "Se devuelve una orden de compra libro a partir de su identificador.")
    @ApiResponse(
        responseCode = "200",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
	@ApiResponse(
        responseCode = "404",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
        description = "No se ha encontrado la orden del libro con el identificador indicado.")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {

        Order order = service.getOrder(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/orders")
    @Operation(
            operationId = "Insertar una orden de compra libro",
            description = "Operacion de escritura",
            summary = "Se crea una orden de compra libro a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del libro a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderRequest.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el libro con el identificador indicado.")
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderRequest request) { 
        Order created = service.createOrder(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
