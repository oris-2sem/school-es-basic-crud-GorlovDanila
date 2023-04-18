package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Parent;
import org.example.entity.Student;
import org.example.repository.ParentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class ParentDataService {
    private ParentRepository parentRepository;

    @Transactional
    public void save(Parent parent) {
        parentRepository.save(parent);
    }

    @Transactional
    public void delete(Parent parent) {
        parentRepository.delete(parent);
    }

    @Transactional
    public Optional<Parent> findById(Long id) {
        return parentRepository.findById(id);
    }

    @Transactional
    public Iterable<Parent> findAll() {
        return parentRepository.findAll();
    }

    @Transactional
    public void update(Long id, String profile, Set<Student> students) {
        Optional<Parent> parent = parentRepository.findById(id);
        parent.ifPresent(currentParent -> {
            currentParent.setStudents(students);
            currentParent.setProfile(profile);
        });
    }

    public Set<Parent> getParentsById(Set<Long> parentsId) {
        Set<Parent> parents = new HashSet<>();
        for (Long i : parentsId) {
            parentRepository.findById(i).ifPresent(parents::add);
        }
        return parents;
    }
}
