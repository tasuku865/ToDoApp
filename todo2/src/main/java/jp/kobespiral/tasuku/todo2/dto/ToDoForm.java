package jp.kobespiral.tasuku.todo2.dto;

import jp.kobespiral.tasuku.todo2.entity.ToDo;
import lombok.Data;

@Data
public class ToDoForm {
  String title;

  public ToDo toEntity() {
    ToDo t = new ToDo(null, title, null, false, null, null);
    return t;
  }
}
