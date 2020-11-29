package nfjuara.controller;

import nfjuara.service.TodosSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class TodosCtl {

    @Autowired
    TodosSvc todosSvc;

    @GetMapping("/todos")
    public Map<String, Object> findAll(){
        return todosSvc.findAll();
    }

    @GetMapping("/todo/{id}")
    public Map<String, Object> findAllOne(@PathVariable("id") Integer id){
        return todosSvc.findOne(id);
    }

    @PostMapping("/todo")
    public Map<String,Object> insert(@RequestBody Map<String,Object> mapInput){
        return todosSvc.insert(mapInput);
    }

    @PutMapping("/todo/{id}")
    public Map<String, Object> update(@RequestBody Map<String,Object> mapInput, @PathVariable("id") Integer id){
        return todosSvc.update(mapInput, id);
    }


    @DeleteMapping("/todo/{id}")
    public Map<String, Object> delete(@PathVariable("id") Integer id){
        return todosSvc.delete(id);
    }

}
