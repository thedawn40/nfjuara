package nfjuara.dao;

import nfjuara.model.Todos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TodosDao extends JpaRepository<Todos, Integer> {

    @Query("select a from Todos a where a.isActive = '1'")
    public List<Todos> findAllTodos();

    @Query("select a from Todos a where a.id=:id")
    public Todos findOne(@Param("id")Integer id);

    @Modifying
    @Query(value="insert into todos (name) values (:name)", nativeQuery = true)
    @Transactional
    public void insert(@Param("name")String name);

    @Modifying
    @Query(value="update todos set name=:name, is_finished=:isFinished, is_active=:isActive, updated_at=now() where id=:id", nativeQuery = true)
    @Transactional
    public void update(@Param("name")String name, @Param("isFinished")Integer isFinished, @Param("isActive")Integer isActive, @Param("id")Integer id);

    @Modifying
    @Query(value="update todos set is_active='0', updated_at=now() where id=:id", nativeQuery = true)
    @Transactional
    public void delete(@Param("id")Integer id);

}
