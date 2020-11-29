package nfjuara.dao;

import nfjuara.model.TodosTags;
import nfjuara.model.TodosTagsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodosTagsDao extends JpaRepository<TodosTags, TodosTagsPK> {

//    @Query("select a from TodosTags a")
//    public List<TodosTags> findAll();

}
