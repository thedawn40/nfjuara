package nfjuara.service;

import java.util.Map;

public interface TodosSvc {

    public Map<String, Object> findAll();
    public Map<String, Object> insert(Map<String, Object> mapInput);
    public Map<String, Object> findOne(Integer id);
    public Map<String, Object> update(Map<String, Object> mapInput, Integer id);
    public Map<String, Object> delete(Integer id);

}
