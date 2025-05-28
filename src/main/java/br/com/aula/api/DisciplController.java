package br.com.aula.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller // This annotation indicates that this class is a Spring MVC controller
@RequestMapping(path = "/discipl") // This specifies the base URL for all methods in this controller
public class DisciplController {

    @Autowired
    private DisciplRepository repository;

    @PostMapping(path="/add")
    public @ResponseBody String addDisciplina(
            @RequestParam String name,
            @RequestParam String teacher,
            @RequestParam String dateStart,
            @RequestParam String dateEnd,
            @RequestParam Integer hours,
            Integer grade) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start;
        LocalDate end;
        try {
            start = LocalDate.parse(dateStart, formatter);
            end = LocalDate.parse(dateEnd, formatter);
        } catch (DateTimeParseException e) {
            return "Formato de data inválido! Use dd/MM/yyyy.";
        }

        Discipl disciplina = new Discipl();
        disciplina.setName(name);
        disciplina.setTeacher(teacher);
        disciplina.setDateStart(start);
        disciplina.setDateEnd(end);
        disciplina.setHours(hours);
        disciplina.setGrade(grade);
        repository.save(disciplina); // Save the disciplina object to the database
        
        return "Disciplina adicionada com sucesso!";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Discipl> getAllDisciplines() {
        // This method returns all disciplines from the database
        return repository.findAll(); // Returns an iterable of Discipl objects
    }
    
    @DeleteMapping(path="/all/{id}")
    public @ResponseBody String deleteDisciplina(@PathVariable("id") Integer id) {
        // This method deletes a discipline by its ID
        if (repository.existsById(id)) {
            repository.deleteById(id); // Deletes the discipline with the given ID
            return "Disciplina deletada com sucesso!";
        } else {
            return "Disciplina não encontrada!";
        }
    }
    
    @PutMapping(path = "/all/{id}")
    public @ResponseBody String editDisciplina(
            @PathVariable("id") Integer id, 
            @RequestBody(required = false) Discipl entity
            ) {
        // This method updates a discipline by its ID
       Discipl disciplina = repository.findById(id)
        .orElseThrow(() -> new IllegalStateException("Disciplina com ID " + id + " não encontrada!"));
        if (entity.getName() != null) {
            disciplina.setName(entity.getName());
        }
        if (entity.getTeacher() != null) {
            disciplina.setTeacher(entity.getTeacher());
        }
        if (entity.getDateStart() != null) {
            disciplina.setDateStart(entity.getDateStart());
        }
        if (entity.getDateEnd() != null) {
            disciplina.setDateEnd(entity.getDateEnd());
        }
        if (entity.getHours() != null) {
            disciplina.setHours(entity.getHours());
        }
        if (entity.getGrade() != null) {
            disciplina.setGrade(entity.getGrade());
        }
        repository.save(disciplina); // Save the updated disciplina object to the database
        return "Disciplina atualizada com sucesso!";
        
    }
}
