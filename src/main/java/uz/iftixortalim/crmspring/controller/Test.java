package uz.iftixortalim.crmspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@CrossOrigin("http://localhost:8081")
public class Test {
    @GetMapping
    public List<String> g(){
        return List.of("adsasd","wer","qwer");
    }

    @GetMapping("/map")
    public ResponseEntity<Map<Long,Boolean>> map(){
        Map<Long,Boolean> map = new HashMap<>();
        map.put(1L,true);
        map.put(2L,false);
        map.put(3L,true);
        map.put(4L,true);
        map.put(5L,false);
        map.put(6L,true);
        map.put(7L,true);
        map.put(8L,true);

        return ResponseEntity.ok(map);
    }
}
