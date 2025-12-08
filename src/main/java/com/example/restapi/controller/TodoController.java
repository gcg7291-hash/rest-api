package com.example.restapi.controller;

import com.example.restapi.dto.request.TodoCreateRequest;
import com.example.restapi.dto.request.TodoUpdateRequest;
import com.example.restapi.dto.response.ApiResponse;
import com.example.restapi.dto.response.TodoResponse;
import com.example.restapi.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<ApiResponse<TodoResponse>> create(
            @Valid @RequestBody TodoCreateRequest request  // FORM 기준으로 작성 으로 @RequestBody 붙임으로 body 영역에서 찾게됨
    ) {
        TodoResponse response = todoService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(ApiResponse.success(response)); //ResponseEntity 상세하게 html 코드를 담아서 보여줌 201 Created
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TodoResponse>>> findAll() {
        List<TodoResponse> responses = todoService.findAll();
        return ResponseEntity.ok(ApiResponse.success(responses));  // ok HttpStatus 200 이라는 뜻
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponse>> findById(
            @PathVariable Long id
    ) {
        TodoResponse response = todoService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id
    ) {
        todoService.delete(id);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody TodoUpdateRequest request
    ) {
        TodoResponse response = todoService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }


}
