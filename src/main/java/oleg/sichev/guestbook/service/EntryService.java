package oleg.sichev.guestbook.service;

import oleg.sichev.guestbook.model.Entry;
import oleg.sichev.guestbook.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntryService {
    @Autowired
    private EntryRepository entryRepository;

    public List<Entry> findAll() {
        return entryRepository.findAll();
    }

    public Entry save(Entry entry) {
        return entryRepository.save(entry);
    }
}