package nfjuara.controller;

import nfjuara.service.TagsSvc;
import nfjuara.service.TodosSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/app")
public class TagsCtl {

    @Autowired
    TagsSvc tagsSvc;

    @GetMapping("/tags")
    public Map<String, Object> findAll() {
        return tagsSvc.findAll();
    }

    @GetMapping("/tag/{id}")
    public Map<String, Object> findAllOne(@PathVariable("id") Integer id) {
        return tagsSvc.findOne(id);
    }

    @PostMapping("/tag")
    public Map<String, Object> insert(@RequestBody Map<String, Object> mapInput) {
        return tagsSvc.insert(mapInput);
    }

    @PutMapping("/tag/{id}")
    public Map<String, Object> update(@RequestBody Map<String, Object> mapInput, @PathVariable("id") Integer id) {
        return tagsSvc.update(mapInput, id);
    }


    @DeleteMapping("/tag/{id}")
    public Map<String, Object> delete(@PathVariable("id") Integer id) {
        return tagsSvc.delete(id);
    }

}
