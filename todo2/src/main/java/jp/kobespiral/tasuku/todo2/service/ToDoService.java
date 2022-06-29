package jp.kobespiral.tasuku.todo2.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kobespiral.tasuku.todo2.dto.SeqForm;
import jp.kobespiral.tasuku.todo2.dto.ToDoForm;
import jp.kobespiral.tasuku.todo2.entity.ToDo;
import jp.kobespiral.tasuku.todo2.repository.ToDoRepository;

@Service
public class ToDoService {

  @Autowired
  ToDoRepository toDoRepository;

  public ToDo createToDo(String mid, ToDoForm form) {

    Date now = new Date();

    return toDoRepository.save(new ToDo(null, form.getTitle(), mid, false, now, null));

  }

  public ToDo getToDo(Long seq) {
    return toDoRepository.findById(seq).orElseThrow();
  }

  public List<ToDo> getToDoList(String mid) {
    return toDoRepository.findByMidAndDone(mid, false);
  }

  public List<ToDo> getDoneList(String mid) {
    return toDoRepository.findByMidAndDone(mid, true);
  }

  public List<ToDo> getToDoList() {
    return toDoRepository.findByDone(false);
  }

  public List<ToDo> getDoneList() {
    return toDoRepository.findByDone(true);
  }

  public ToDo doneToDo(SeqForm form) {

    ToDo todo = toDoRepository.findById(form.getSeq()).orElseThrow();
    todo.setDone(true);
    Date now = new Date();
    todo.setDoneAt(now);
    return toDoRepository.save(todo);

  }

  @Transactional
  public void deleteToDo(SeqForm form) {
    toDoRepository.deleteById(form.getSeq());
  }
}
