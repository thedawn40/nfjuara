package nfjuara.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "todos_tags")
public class TodosTags implements Serializable {

    @EmbeddedId
    private TodosTagsPK todosTagsPK;

    public TodosTagsPK getTodosTagsPK() {
        return todosTagsPK;
    }

    public void setTodosTagsPK(TodosTagsPK todosTagsPK) {
        this.todosTagsPK = todosTagsPK;
    }
}
