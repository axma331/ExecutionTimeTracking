package t1.ismailov.timetracking.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t1.ismailov.timetracking.example.service.TestService;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @Operation(summary = "Запуск методов для проверки")
    @GetMapping("/time/methods/test")
    public ResponseEntity<Void> test() {
        testService.testTime();
        testService.testAsyncTime();
        return ResponseEntity.ok().build();
    }
}
