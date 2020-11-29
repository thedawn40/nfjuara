package nfjuara.service.impl;

import nfjuara.dao.TagsDao;
import nfjuara.dao.TodosDao;
import nfjuara.dao.TodosTagsDao;
import nfjuara.dto.TodosDto;
import nfjuara.model.Tags;
import nfjuara.model.Todos;
import nfjuara.model.TodosTags;
import nfjuara.service.TagsSvc;
import nfjuara.service.TodosSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TodosSvcImpl implements TodosSvc {

    @Autowired
    TodosDao todosDao;

    @Autowired
    TodosTagsDao todosTagsDao;

    @Autowired
    TagsDao tagsDao;

    @Override
    public Map<String, Object> findAll() {
        Map<String, Object> map = new HashMap<>();
        try{
            List<TodosDto> listTodosDto = new ArrayList<>();
            List<Todos> listTodos = todosDao.findAllTodos();
            List<TodosTags> listTodosTags = todosTagsDao.findAll();
            for(Todos x : listTodos){
                TodosDto dto = new TodosDto();
                List<Tags> listTagsTemp = new ArrayList<>();
                for(TodosTags y : listTodosTags){
                    if(x.getId()==y.getTodosTagsPK().getTodoId()){
                        listTagsTemp.add(tagsDao.findOne(y.getTodosTagsPK().getTagsId()));
                    }
                }
                dto.setCreatedAt(x.getCreatedAt());
                dto.setId(x.getId());
                dto.setIsActive(x.getIsActive());
                dto.setIsFinished(x.getIsFinished());
                dto.setName(x.getName());
                dto.setUpdatedAt(x.getUpdatedAt());
                dto.setTags(listTagsTemp);
                listTodosDto.add(dto);
            }
            map.put("status", "1");
            map.put("messages", "success");
            map.put("totalRecords", listTodosDto.size());
            map.put("contents", listTodosDto);
            return map;
        }catch(Exception e){
            e.printStackTrace();
            map.put("status", "0");
            map.put("messages", "failed load data, info "+e.getMessage());
            return map;
        }
    }

    @Override
    public Map<String, Object> insert(Map<String, Object> mapInput) {
        Map<String, Object> map = new HashMap<>();
        try{
            todosDao.insert(mapInput.get("name").toString());
            map.put("status", "1");
            map.put("messages", "success insert data");
            return map;
        }catch(Exception e){
            e.printStackTrace();
            map.put("status", "0");
            map.put("messages", "failed insert, info "+e.getMessage());
            return map;
        }
    }

    @Override
    public Map<String, Object> findOne(Integer id) {
        Map<String, Object> map = new HashMap<>();
        try{
            Todos todo = todosDao.findOne(id);
            if(todo==null||todo.getId()==null){
                map.put("messages", "data dengan id "+id+" tidak ditemukan");
            }else{
                map.put("messages", "success");
                map.put("contents", todo);
            }
            map.put("status", "1");
            return map;
        }catch(Exception e){
            e.printStackTrace();
            map.put("status", "0");
            map.put("messages", e.getMessage());
            return map;
        }
    }

    @Override
    public Map<String, Object> update(Map<String, Object> mapInput, Integer id) {
        Map<String, Object> map = new HashMap<>();
        try{
            Todos todo = todosDao.findOne(id);
            if(todo==null||todo.getId()==null){
                map.put("messages", "data dengan id "+id+" tidak ditemukan");
            }else{
                todosDao.update(mapInput.get("name").toString(),
                        Integer.valueOf(mapInput.get("isFinished").toString()),
                        Integer.valueOf(mapInput.get("isActive").toString()),
                        id);
                map.put("messages", "success update data");
            }
            map.put("status", "1");
            return map;
        }catch(Exception e){
            e.printStackTrace();
            map.put("status", "0");
            map.put("messages", "failed update, info "+e.getMessage());
            return map;
        }
    }

    @Override
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new HashMap<>();
        try{
            Todos todo = todosDao.findOne(id);
            if(todo==null||todo.getId()==null){
                map.put("messages", "data dengan id "+id+" tidak ditemukan");
            }else{
                todosDao.delete(id);
                map.put("messages", "delete success");
            }
            map.put("status", "1");
            return map;
        }catch(Exception e){
            e.printStackTrace();
            map.put("status", "0");
            map.put("messages", "failed delete, info "+e.getMessage());
            return map;
        }
    }
}
