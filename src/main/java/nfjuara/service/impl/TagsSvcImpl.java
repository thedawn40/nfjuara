package nfjuara.service.impl;

import nfjuara.dao.TagsDao;
import nfjuara.dao.TodosDao;
import nfjuara.dao.TodosTagsDao;
import nfjuara.model.Tags;
import nfjuara.model.Todos;
import nfjuara.model.TodosTags;
import nfjuara.service.TagsSvc;
import nfjuara.service.TodosSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagsSvcImpl implements TagsSvc {

    @Autowired
    TagsDao tagsDao;

    @Override
    public Map<String, Object> findAll() {
        Map<String, Object> map = new HashMap<>();
        try{
            List<Todos> listTodos = tagsDao.findAllTags();
            map.put("status", "1");
            map.put("messages", "success");
            map.put("totalRecords", listTodos.size());
            map.put("contents", listTodos);
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
            tagsDao.insert(mapInput.get("name").toString());
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
            Tags tags = tagsDao.findOne(id);
            if(tags==null || tags.getId()==null){
                map.put("messages", "data dengan id "+id+" tidak ditemukan");
            }else{
                map.put("messages", "success");
                map.put("contents", tags);
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
            Tags tags = tagsDao.findOne(id);
            if(tags==null || tags.getId()==null){
                map.put("messages", "tag id tidak ditemukan");
            }else{
                tagsDao.update(mapInput.get("name").toString(),
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
            Tags tags = tagsDao.findOne(id);
            if(tags==null || tags.getId()==null){
                map.put("messages", "tag id tidak ditemukan");
            }else{
                tagsDao.delete(id);
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
