package com.example.todolist.service;

import com.example.todolist.annotation.BenchmarkTime;
import com.example.todolist.annotation.LogParameters;
import com.example.todolist.annotation.TransactionalService;
import com.example.todolist.exceptions.InvalidDescriptionException;
import com.example.todolist.model.Status;
import com.example.todolist.model.ToDoItem;
import com.example.todolist.repository.ToDoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static com.example.todolist.Utils.isValidDescription;

@Slf4j
@TransactionalService
public class TodoService {

    @Autowired
    private ToDoRepository todoRepository;

    @BenchmarkTime
    public List<ToDoItem> getAllToDoItems() {
        return todoRepository.findAll();
    }

    @BenchmarkTime
    public Optional<ToDoItem> getToDoItem(long id) {
        Optional<ToDoItem> item = todoRepository.findById(id);
        if(item.isPresent()) {
            log.info(String.format("    Item %d - Found: %s", id, item.get()));
        } else {
            log.info(String.format("    Item %d - Not Found", id));
        }
        return item;
    }

    @BenchmarkTime
    @LogParameters
    public Long createToDoItem(ToDoItem item) throws InvalidDescriptionException {
        String description = item.getDescription();
        if(isValidDescription(description) == false) {
            throw new InvalidDescriptionException();
        }
        item.setStatus(Status.pending);
        ToDoItem createdItem = todoRepository.save(item);
        log.info(String.format("    Item %d - Created: %s", createdItem.getId(), createdItem));
        return createdItem.getId();
    }

    @LogParameters
    public long updateOrCreateToDoItem(long id, ToDoItem item) throws InvalidDescriptionException {
        Optional<ToDoItem> searchItem = getToDoItem(id);
        if(searchItem.isPresent()) {
            ToDoItem actualItem = searchItem.get();
            actualItem.update(item);

            ToDoItem updatedItem = todoRepository.save(actualItem);
            log.info(String.format("    Item %d - Updated: %s", updatedItem.getId(), updatedItem));
            return updatedItem.getId();
        }
        return createToDoItem(item);
    }

    @LogParameters
    public void deleteToDoItem(long id) {
        if(getToDoItem(id).isPresent()) {
            todoRepository.deleteById(id);
            log.info(String.format("    Item %d - Deleted", id));
        }
    }
}
