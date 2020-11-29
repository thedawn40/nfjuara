package nfjuara.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.io.Serializable;

@Embeddable
public class TodosTagsPK implements Serializable {

    @Column(name="todo_id")
    private Integer todoId;

    @Column(name="tags_id")
    private Integer tagsId;

    public Integer getTagsId() {
        return tagsId;
    }

    public void setTagsId(Integer tagsId) {
        this.tagsId = tagsId;
    }

    public Integer getTodoId() {
        return todoId;
    }

    public void setTodoId(Integer todoId) {
        this.todoId = todoId;
    }
}
