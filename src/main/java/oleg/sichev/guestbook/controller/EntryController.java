package oleg.sichev.guestbook.controller;
import oleg.sichev.guestbook.model.Entry;
import oleg.sichev.guestbook.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EntryController {
    @Autowired
    private EntryService entryService;

    @GetMapping("/")
    public String showGuestBook(Model model) {
        model.addAttribute("entries", entryService.findAll());
        model.addAttribute("entry", new Entry());
        return "guestbook";
    }

    @PostMapping("/")
    public String addEntry(@ModelAttribute Entry entry) {
        entryService.save(entry);
        return "redirect:/";
    }
}