package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DummyController {

    // 1. Illegal Argument
    @GetMapping("/illegal-arg")
    public String illegalArgument() {
        throw new IllegalArgumentException("Invalid input provided");
    }

    // 2. Arithmetic Exception
    @GetMapping("/divide")
    public int divideByZero() {
        return 10 / 0;
    }

    // 3. Null Pointer Exception
    @GetMapping("/npe")
    public int nullPointer() {
        String str = null;
        return str.length();
    }

    // 4. Array Index Out Of Bounds
    @GetMapping("/array-index")
    public int arrayIndexError() {
        int[] arr = new int[3];
        return arr[5];
    }

    // 5. Number Format Exception
    @GetMapping("/number-format")
    public int numberFormat() {
        return Integer.parseInt("abc");
    }

    // 6. Class Cast Exception
    @GetMapping("/class-cast")
    public String classCast() {
        Object obj = 123;
        return (String) obj;
    }

    // 7. File Not Found Exception
    @GetMapping("/file-not-found")
    public String fileNotFound() throws Exception {
        throw new FileNotFoundException("File not found in system");
    }

    // 8. Simulated Database Exception
    @GetMapping("/db-error")
    public String databaseError() throws Exception {
        throw new SQLException("Database connection failed");
    }

    // 9. Timeout Simulation
    @GetMapping("/timeout")
    public String timeout() throws InterruptedException {
        Thread.sleep(10000); // 10 sec delay
        return "Completed (but too late)";
    }

    // 10. Custom Runtime Exception
    @GetMapping("/custom-error")
    public String customError() {
        throw new RuntimeException("Something went wrong in business logic");
    }

    // 11. Stack Overflow (recursive call)
    @GetMapping("/stack-overflow")
    public int stackOverflow() {
        return recursiveCall();
    }

    private int recursiveCall() {
        return recursiveCall();
    }

    // 12. Concurrent Modification Exception
    @GetMapping("/concurrent-mod")
    public String concurrentModification() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");

        for (String key : map.keySet()) {
            map.put("key2", "value2"); // modification during iteration
        }
        return "Done";
    }

    // 13. Out Of Memory Simulation (careful!)
    @GetMapping("/oom")
    public String outOfMemory() {
        int[] arr = new int[Integer.MAX_VALUE];
        return "Allocated";
    }
}