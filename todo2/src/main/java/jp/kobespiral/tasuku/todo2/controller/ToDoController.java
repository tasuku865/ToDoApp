package jp.kobespiral.tasuku.todo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.kobespiral.tasuku.todo2.dto.MidForm;
import jp.kobespiral.tasuku.todo2.dto.SeqForm;
import jp.kobespiral.tasuku.todo2.dto.ToDoForm;
import jp.kobespiral.tasuku.todo2.service.MemberService;
import jp.kobespiral.tasuku.todo2.service.ToDoService;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ToDoController {
  @Autowired
  ToDoService toDoService;

  /**
   * 管理者用・ユーザ登録ページ HTTP-GET /admin/register
   * 
   * @param model
   * @return
   */
  @GetMapping("/")
  String showIndex(Model model) {
    MidForm form = new MidForm();
    model.addAttribute("MidForm", form);
    return "index";
  }

  @GetMapping("/login")
  String checkMidForm(@ModelAttribute(name = "MidForm") MidForm form, Model model) {

    return "redirect:/users/" + form.getMid();
  }

  @GetMapping("/users/{mid}")
  String showUsersView(@PathVariable String mid, Model model) {
    ToDoForm tform = new ToDoForm();
    SeqForm sform = new SeqForm();
    MidForm mform = new MidForm();
    model.addAttribute("mid", mid);
    model.addAttribute("ToDoForm", tform);
    model.addAttribute("SeqForm", sform);
    model.addAttribute("MidForm", mform);
    model.addAttribute("ToDos", toDoService.getToDoList(mid));
    model.addAttribute("Dones", toDoService.getDoneList(mid));

    return "userView";
  }

  @PostMapping("/users/{mid}/todos")
  String postToDo(@ModelAttribute(name = "ToDoForm") ToDoForm form, @PathVariable String mid, Model model) {
    toDoService.createToDo(mid, form);
    return "redirect:/users/" + mid;
  }

  @PostMapping("/users/{mid}/todos/done")
  String doneToDo(@ModelAttribute(name = "SeqForm") SeqForm form, @PathVariable String mid, Model model) {
    toDoService.doneToDo(form);
    return "redirect:/users/" + mid;
  }

  @PostMapping("/users/{mid}/todos/delete")
  String deleteToDo(@ModelAttribute(name = "SeqForm") SeqForm form, @PathVariable String mid, Model model) {
    toDoService.deleteToDo(form);
    return "redirect:/users/" + mid;
  }

  @GetMapping("/users/todos")
  String showAllUsersTodo(@ModelAttribute(name = "MidForm") MidForm form, Model model) {
    model.addAttribute("mid", form.getMid());
    model.addAttribute("ToDos", toDoService.getToDoList());
    model.addAttribute("Dones", toDoService.getDoneList());
    return "allUsersToDo";
  }
}
